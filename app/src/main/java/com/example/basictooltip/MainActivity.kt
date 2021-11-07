package com.example.basictooltip

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.basictooltip.databinding.ActivityMainBinding
import com.tomergoldst.tooltips.ToolTip
import com.tomergoldst.tooltips.ToolTipsManager

class MainActivity : AppCompatActivity(), ToolTipsManager.TipListener {

    /** Binding */
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    /** Tooltip Manager */
    private lateinit var tooltipManager: ToolTipsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initListeners()
        initTooltipManager()

    }

    private fun initListeners() = with(binding) {

        aboveButton.setOnClickListener {
            val position = ToolTip.POSITION_ABOVE // position 정의
            val align = ToolTip.ALIGN_CENTER // 정렬 정의
            displayTooltip(position, align)
        }

        rightButton.setOnClickListener {
            val position = ToolTip.POSITION_RIGHT_TO// position 정의
            val align = ToolTip.ALIGN_CENTER // 정렬 정의
            displayTooltip(position, align)
        }

        leftButton.setOnClickListener {
            val position = ToolTip.POSITION_LEFT_TO// position 정의
            val align = ToolTip.ALIGN_CENTER // 정렬 정의
            displayTooltip(position, align)
        }

        belowButton.setOnClickListener {
            val position = ToolTip.POSITION_BELOW // position 정의
            val align = ToolTip.ALIGN_CENTER // 정렬 정의
            displayTooltip(position, align)
        }

        areaTextView.setOnClickListener {
            // 툴팁끄기
            tooltipManager.dismissAll()
        }

        root.setOnClickListener {
            // 툴팁끄기
            tooltipManager.dismissAll()
        }

    }

    private fun initTooltipManager() {
        tooltipManager = ToolTipsManager(this)
    }

    private fun displayTooltip(position: Int, align: Int) {
        val message = binding.messageEditText.text.toString().trim()
        // 텍스트뷰에 tooltip 설정
        tooltipManager.findAndDismiss(binding.areaTextView)
        if (message.isNotEmpty()) {
            val builder = ToolTip.Builder(
                this,
                binding.areaTextView,
                binding.root,
                message,
                position
            ).apply {
                // 정렬하기
                setAlign(align)
                // 백그라운드 컬러 지정
                setBackgroundColor(Color.BLUE)
            }
            // 툴팁 보여주기
            tooltipManager.show(builder.build())
        } else {
            // edittext가 비어져있으면
            Toast.makeText(this, "메세지를 입력해주세요", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onTipDismissed(view: View?, anchorViewId: Int, byUser: Boolean) {
        // check condition
        if (byUser) {
            // 유저가 tooltip을 끄면
            Toast.makeText(this, "툴팁 종료", Toast.LENGTH_SHORT).show()
        }

    }

}
