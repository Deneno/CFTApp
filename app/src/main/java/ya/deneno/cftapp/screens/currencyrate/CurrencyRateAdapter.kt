package ya.deneno.cftapp.screens.currencyrate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ya.deneno.cftapp.APP
import ya.deneno.cftapp.R
import ya.deneno.cftapp.databinding.ItemCurrencyLayoutBinding
import ya.deneno.cftapp.model.CurrencyModel

class CurrencyRateAdapter: RecyclerView.Adapter<CurrencyRateAdapter.CurrencyRateViewHolder>() {

    private var currencyList = emptyList<CurrencyModel>()

    class CurrencyRateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCurrencyLayoutBinding.bind(view)

        fun bind(currencyModel: CurrencyModel) {
            binding.apply {
                itemName.text = "${currencyModel.Nominal} ${currencyModel.Name} (${currencyModel.CharCode}), рублей"
                itemRate.text = "${currencyModel.Value} (${String.format("%.2f", (currencyModel.Value-currencyModel.Previous)/currencyModel.Previous*100)}%)"
                itemPrevRate.text = currencyModel.Previous.toString()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyRateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_currency_layout, parent, false)

        return CurrencyRateViewHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyRateViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("currentCurrency", currencyList[holder.adapterPosition])
            APP.navController.navigate(R.id.action_currencyRateFragment_to_converterFragment, bundle)
        }
        holder.bind(currencyList[position])
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    fun setList(list: List<CurrencyModel>) {
        currencyList = list.sortedBy { it.Name }
        notifyDataSetChanged()
    }
}