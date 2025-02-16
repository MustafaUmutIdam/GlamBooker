package com.example.glambooker.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.glambooker.MainActivity
import com.example.glambooker.data.entity.Adress
import com.example.glambooker.data.entity.Workplace
import com.example.glambooker.databinding.FragmentBeBossBinding
import com.example.glambooker.ui.viewmodel.BeBossViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task


class BeBossFragment : Fragment() {
    private lateinit var binding:FragmentBeBossBinding
    private lateinit var viewModel: BeBossViewModel
    private var selectedCity: String? = null
    private var checkPermission = 0
    private lateinit var flpc:FusedLocationProviderClient
    private lateinit var locationTask:Task<Location>

    @SuppressLint("MissingPermission")
    private val locationPermissionRequest = registerForActivityResult(//İzin onay Sonucu gösteriyor
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->

        if (isGranted) {//Kullanici izni verdi

            locationTask = flpc.lastLocation
            getLocation()//Konumu al
        }
        else {//Kullanici izni reddetti
            Toast.makeText(requireContext(), "İzin Reddedildi", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        binding = FragmentBeBossBinding.inflate(inflater, container, false)

        //Baslangıcta direkt calismali
        flpc = LocationServices.getFusedLocationProviderClient(requireContext())

        viewModel.adressList.observe(viewLifecycleOwner) { list ->
            list?.let {
                val cities = ArrayList<String>()
                //İlk sehir temp'e eklendi ,temp listeye eklendi , dosyada sıradaki sehirle aynı olmayana kadar ekleme olmadı
                var temp = it[0].city
                cities.add(temp)
                for (a in it) {
                    if (temp != a.city) {
                        temp = a.city
                        cities.add(temp)
                    }
                }

                val arrayAdapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    cities)
                binding.autoCompleteCities.setAdapter(arrayAdapter)
                selectedCity = "ADANA"
                //Seçilen şehri burda almalıyım
                binding.autoCompleteCities.setOnItemClickListener { parent, view, position, id ->
                    selectedCity  = arrayAdapter.getItem(position) ?: "KAYSERİ"
                    Toast.makeText(requireContext() , selectedCity,Toast.LENGTH_LONG).show()
                    selectedCity?.let { city -> updateTowns(city ,it) }
                }
            }
        }
        val categories = ArrayList<String>()
        categories.add("Berber")
        categories.add("Güzellik Salonları")
        val arrayAdapter =ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,categories )
        binding.autoCompleteCategories.setAdapter(arrayAdapter)

        //Konum alma islemleri
        binding.buttonGetLocation.setOnClickListener{
            val checkPermission = ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            if (checkPermission == PackageManager.PERMISSION_GRANTED) {//Eger izin onceden verilmisse direkt konumu al
                locationTask = flpc.lastLocation
                getLocation()
            }
            else {//Eger izin verilmemisse, yeni API ile izin iste
                locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }

            binding.buttonRegister.setOnClickListener{
                val phone = binding.editTextPhone.text.toString()
                val name = binding.editTextInputName.text.toString()
                val category = binding.autoCompleteCategories.text.toString()
                val city = binding.autoCompleteCities.text.toString()
                val town = binding.autoCompleteTowns.text.toString()

                //Boş yerleri kontrol
                if (phone.isEmpty() || name.isEmpty() || category.isEmpty() || city.isEmpty() || town.isEmpty()) {
                    Toast.makeText(requireContext(), "Lütfen tüm alanları doldurun!", Toast.LENGTH_LONG).show()
                    return@setOnClickListener // İşlemi durdur
                }

                //Dolu olursa
                val workplace = Workplace(
                    "",
                    "1",
                    category,
                    name,
                    binding.editTextInputDetail.text.toString().ifEmpty { "Boş" },
                    city,
                    town,
                    "0",
                    "imageName"
                )
                save(workplace)
                Toast.makeText(requireContext(), "${workplace.name} Kaydedildi", Toast.LENGTH_LONG).show()

                requireActivity().onBackPressedDispatcher.onBackPressed()
            }

        return binding.root
    }

    private fun save(workplace: Workplace) {
        viewModel.saveWorkplace(workplace)

    }
    private fun updateTowns(selectedCity :String, cityDistrictList: List<Adress>) {
        val towns = cityDistrictList.filter { it.city == selectedCity }.map { it.town }
        val townAdapter= ArrayAdapter(requireContext(),android.R.layout.simple_dropdown_item_1line,towns)
        binding.autoCompleteTowns.setAdapter(townAdapter)

    }
    private fun getLocationPermission(){
        checkPermission = ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.ACCESS_FINE_LOCATION)
        if(checkPermission == PackageManager.PERMISSION_GRANTED){//İzin onaylanmissa
        }
        else{ //Onayli degilse burda build oluyor
            ActivityCompat.requestPermissions(MainActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),100)
        }
    }
    private fun getLocation(){
        locationTask.addOnSuccessListener {
            if(it!=null){//Konum Doluysa
                binding.textView.text = "Enlem:${it.latitude} ,, Boylam:${it.longitude}"
            }
            else{//Konum Dolu degilse
                binding.textView.text = "Konum yok"
            }
        }
    }

    //ViewModel direkt kullanılamadığı için burası şart !!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: BeBossViewModel by viewModels()
        viewModel = tempViewModel

    }
}