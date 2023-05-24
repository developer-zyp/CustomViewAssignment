package com.zyp.customviewassignment.views.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.zyp.customviewassignment.R

class CircleProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {

        private const val DEFAULT_PROGRESS_COLOR = Color.RED
        private const val DEFAULT_THICKNESS = 12f
        private const val DEFAULT_PROGRESS = 0
        private const val DEFAULT_MAX_PROGRESS = 100
        private const val DEFAULT_TEXT_SIZE = 48f

    }

    private var progress = DEFAULT_PROGRESS // Current progress value
    private var maxProgress = DEFAULT_MAX_PROGRESS
    private var thickness = DEFAULT_THICKNESS
    private var progressColor = DEFAULT_PROGRESS_COLOR
    private var progressTextSize = DEFAULT_TEXT_SIZE

    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        setUpAttributes(attrs)
    }

    private fun setUpAttributes(attrs: AttributeSet?) {
        context.withStyledAttributes(attrs, R.styleable.CircleProgressView) {
            progressColor = getColor(
                R.styleable.CircleProgressView_progressColor,
                DEFAULT_PROGRESS_COLOR
            )
            thickness = getDimension(R.styleable.CircleProgressView_thickness, DEFAULT_THICKNESS)
            progress = getInteger(R.styleable.CircleProgressView_progress, DEFAULT_PROGRESS)
            maxProgress = getInteger(
                R.styleable.CircleProgressView_maxProgress,
                DEFAULT_MAX_PROGRESS
            )
            progressTextSize = getDimension(
                R.styleable.CircleProgressView_progressTextSize,
                DEFAULT_TEXT_SIZE
            )
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        progressPaint.apply {
            color = progressColor // Set the color for the progress
            style = Paint.Style.STROKE // Set the paint style to stroke
            strokeWidth = thickness // Set the stroke width
            strokeCap = Paint.Cap.ROUND // Set the stroke cap to round for rounded edges
            isAntiAlias = true
        }

        val centerX = width / 2f
        val centerY = height / 2f
        val radius = (width.coerceAtMost(height) - progressPaint.strokeWidth) / 2f

        // Draw the background circle
        progressPaint.alpha = 50 // Set the alpha value for the background
        canvas?.drawCircle(centerX, centerY, radius, progressPaint)

        // Draw the progress arc
        progressPaint.alpha = 255 // Set the alpha value for the progress
        val startAngle = -90f // Start angle for the progress arc (top center)
        val sweepAngle = 360f * progress / maxProgress // Sweep angle based on progress percentage
        canvas?.drawArc(
            centerX - radius, centerY - radius, centerX + radius, centerY + radius,
            startAngle, sweepAngle, false, progressPaint
        )

        // Draw the text
        textPaint.apply {
            color = progressColor
            textSize = progressTextSize
        }

        val text = "$progress%"
        val textWidth = textPaint.measureText(text)
        val textX = centerX - textWidth / 2f
        val textY = centerY + textPaint.textSize / 3f
        canvas?.drawText(text, textX, textY, textPaint)

    }

    fun setProgress(progress: Int) {
        this.progress = progress
        invalidate() // Redraw the view
    }

}