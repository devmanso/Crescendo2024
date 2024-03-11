// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public class DrivingFilter {
    private double last;

    public DrivingFilter() {
        last = 0.0;
    }

    public double get(double next) {
        double sign = (next > 0) ? 1 : -1;
        next *= next * sign;

        return last += (next - last) * (1 - 0.25); // .25 works
    }
}
