package com.hysea.animator.propertyAnimator;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by hysea on 2018/8/24.
 */
public class BezierEvaluator implements TypeEvaluator<PointF> {
    private PointF point1;
    private PointF point2;

    public BezierEvaluator(PointF point1, PointF point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    @Override
    public PointF evaluate(float time, PointF start, PointF end) {
        float timeLeft = 1.0f - time;
        PointF pointF = new PointF();

        pointF.x = timeLeft * timeLeft * timeLeft * (start.x)
                + 3 * timeLeft * timeLeft * time * (point1.x)
                + 3 * timeLeft * time * time * (point2.x)
                + time * time * time * (end.x);

        pointF.y = timeLeft * timeLeft * timeLeft * (start.y)
                + 3 * timeLeft * timeLeft * time * (point1.y)
                + 3 * timeLeft * time * time * (point2.y)
                + time * time * time * (end.y);
        return pointF;
    }

}
