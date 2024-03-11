// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.VideoMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CameraStream extends SubsystemBase {
  /** Creates a new CameraStream. */
  public CameraStream() {}

  Thread streamThread;

  public void streamVideo() {
    streamThread = new Thread(
      () -> {
        var camera = CameraServer.startAutomaticCapture();
        // 320 x 240 before
        var cameraWidth = 160;
        var cameraHeight = 120;
        //camera.setPixelFormat(PixelFormat.kGray);

        camera.setResolution(cameraWidth, cameraHeight);
        camera.setFPS(60);

        var red = new Scalar(0, 0, 255);
        var crosshairPoint = new Point();

        var cvSink = CameraServer.getVideo();
        var outputStream = CameraServer.putVideo("Driver Station",
         cameraWidth, cameraHeight);
        
         // mats are memory expensive, it's best to just use one
        var mat = new Mat();
        // this can never be true the robot must be off for this to be true
        while (!Thread.interrupted()) {
          if (cvSink.grabFrame(mat) == 0) {
            outputStream.notifyError(cvSink.getError());
            continue;
          }
          // 220 x 170 before
          crosshairPoint.x = 90;
          crosshairPoint.y = 100;
          // 70 before
          Imgproc.circle(mat, crosshairPoint, 40, red);
          long deltaTime = camera.getLastFrameTime();
          SmartDashboard.putNumber("DriverStation Camera delay", deltaTime);
          outputStream.putFrame(mat);
        }

      });
      streamThread.setDaemon(true);
      streamThread.start();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
