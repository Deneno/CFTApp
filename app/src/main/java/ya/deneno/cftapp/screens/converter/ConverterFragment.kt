package ya.deneno.cftapp.screens.converter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ya.deneno.cftapp.APP
import ya.deneno.cftapp.databinding.FragmentConverterBinding
import ya.deneno.cftapp.model.CurrencyModel

class ConverterFragment : Fragment() {
    private lateinit var binding: FragmentConverterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConverterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val currentCurrency = arguments?.getSerializable("currentCurrency") as CurrencyModel
        binding.currentCurrency.text = "${currentCurrency.Nominal} ${currentCurrency.Name}"
        binding.todayCurrencyRate.text = "Текущий курс ${currentCurrency.Value} рублей за ${currentCurrency.Nominal} у.е."

        binding.buttonBack.setOnClickListener{
            APP.navController.popBackStack()
        }
        binding.rubSum.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.rubSum.text.equals("")) {
                    binding.converterResult.visibility = View.INVISIBLE
                } else {
                    binding.converterResult.visibility = View.VISIBLE
                    val result = String.format("%.2f", binding.rubSum.text.toString().toLong() / currentCurrency.Value * currentCurrency.Nominal)
                    binding.converterResult.text = "Это равно $result у.е."
                }
            }
        })
    }
}