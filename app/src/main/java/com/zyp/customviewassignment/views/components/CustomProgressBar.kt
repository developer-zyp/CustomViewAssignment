package com.zyp.customviewassignment.views.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.zyp.customviewassignment.R

class CustomProgressBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {

        private const val DEFAULT_PROGRESS_COLOR = Color.RED
        private const val DEFAULT_THICKNESS = 12f
        private const val DEFAULT_PROGRESS = 0
        private const val DEFAULT_MAX_PROGRESS = 100

    }

    private var progress = DEFAULT_PROGRESS // Current progress value
    private var maxProgress = DEFAULT_MAX_PROGRESS
    private var thickness = DEFAULT_THICKNESS
    private var progressColor = DEFAULT_PROGRESS_COLOR

    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        setUpAttributes(attrs)
    }

    private fun setUpAttributes(attrs: AttributeSet?) {
        context.withStyledAttributes(attrs, R.styleable.CustomProgressBar) {
            progressColor = getColor(
                R.styleable.CustomProgressBar_lineProgressColor,
                DEFAULT_PROGRESS_COLOR
            )
            thickness = getDimension(R.styleable.CustomProgressBar_lineThickness, DEFAULT_THICKNESS)
            progress = getInteger(R.styleable.CustomProgressBar_lineProgress, DEFAULT_PROGRESS)
            maxProgress =
                getInteger(R.styleable.CustomProgressBar_lineMaxProgress, DEFAULT_MAX_PROGRESS)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        progressPaint.apply {
            color = progressColor // Set the color for the progress
//            style = Paint.Style.FILL // Set the paint style to stroke
            strokeWidth = thickness // Set the stroke width
            strokeCap = Paint.Cap.ROUND // Set the stroke cap to round for rounded edges
            isAntiAlias = true
        }

        val width = width.toFloat()
        val height = height.toFloat()

        val startX = 0f
        val startY = height / 2
        val endX = width * progress / maxProgress
        val endY = height / 2

        // Draw the background line
        progressPaint.alpha = 50 // Set the alpha value for the background
        canvas?.drawLine(startX, startY, width, endY, progressPaint)

        // Draw the progress line
        progressPaint.alpha = 255
        canvas?.drawLine(startX, startY, endX, endY, progressPaint)

    }

    fun setProgress(progress: Int) {
        this.progress = progress
        invalidate() // Redraw the view
    }

}