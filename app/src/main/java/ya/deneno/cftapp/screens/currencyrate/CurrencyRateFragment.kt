package ya.deneno.cftapp.screens.currencyrate

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ya.deneno.cftapp.databinding.FragmentCurrencyRateBinding
import java.text.SimpleDateFormat
import java.util.*

class CurrencyRateFragment : Fragment() {
    private lateinit var binding: FragmentCurrencyRateBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CurrencyRateAdapter
    private lateinit var viewModel: CurrencyRateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyRateBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[CurrencyRateViewModel::class.java]
        viewModel.initDataBase()
        recyclerView = binding.rvCurrencyRate
        adapter = CurrencyRateAdapter()
        recyclerView.adapter = adapter
        //Заполнение RecyclerView
        viewModel.getAllCurrency().observe(viewLifecycleOwner) {
            if (it.isEmpty()) viewModel.syncCurrencyRate()
            adapter.setList(it)
        }
        //Кнопка синхронизации
        binding.buttonSync.setOnClickListener{
            viewModel.syncCurrencyRate()
        }
        //Установка даты и Периодическое обновление приложение
        viewModel.getOne().observe(viewLifecycleOwner) { model ->
            if (model != null) {
                val dateRaw = SimpleDateFormat("yyyy-MM-d'T'HH:mm:ss", Locale.getDefault()).parse(model.Date)
                if (dateRaw.time - Date().time < 6900000) {
                    viewModel.syncCurrencyRate()
                    Log.d("myLog", "Обновилось по времени")
                }
                val date = dateRaw?.let {
                    SimpleDateFormat("d.MM.yyyy", Locale.getDefault()).format(it)
                }.toString()
                binding.titleCurrencyRate.text = "Курс валют ЦБ РФ на $date, рублей"
            }
        }
    }
}