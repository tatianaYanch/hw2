package com.example.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.calculator.databinding.FragmentCalculatorBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = requireNotNull(_binding) { "View was destroyed" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCalculatorBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            buttonOne.setOnClickListener { onNumberClick(buttonOne) }
            buttonTwo.setOnClickListener { onNumberClick(buttonTwo) }
            buttonThree.setOnClickListener { onNumberClick(buttonThree) }
            buttonFour.setOnClickListener { onNumberClick(buttonFour) }
            buttonFive.setOnClickListener { onNumberClick(buttonFive) }
            buttonSix.setOnClickListener { onNumberClick(buttonSix) }
            buttonSeven.setOnClickListener { onNumberClick(buttonSeven) }
            buttonEight.setOnClickListener { onNumberClick(buttonEight) }
            buttonNine.setOnClickListener { onNumberClick(buttonNine) }
            buttonZero.setOnClickListener { onNumberClick(buttonZero) }
            buttonDot.setOnClickListener { onNumberClick(buttonDot) }
            buttonDivide.setOnClickListener { onNumberClick(buttonDivide) }
            buttonMultiply.setOnClickListener { onNumberClick(buttonMultiply) }
            buttonPlus.setOnClickListener { onNumberClick(buttonPlus) }
            buttonMinus.setOnClickListener { onNumberClick(buttonMinus) }

            buttonClean.setOnClickListener {
                textOperation.text = ""
                textResult.text = ""
            }

            buttonEqual.setOnClickListener {
                try {
                    val ex = ExpressionBuilder(textOperation.text.toString()).build()
                    val result = ex.evaluate()

                    val longRes = result.toLong()
                    if (result == longRes.toDouble()) {
                        textResult.text = longRes.toString()
                    } else {
                        textResult.text = result.toString()
                    }
                } catch (e: Exception) {
                    Toast.makeText(root.context, "Введено неверное выражение", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun onNumberClick(button: Button) {
        with(binding) {
            if (textResult.text != "") {
                textOperation.text = textResult.text
                textResult.text = ""
            }
            textOperation.append(button.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}