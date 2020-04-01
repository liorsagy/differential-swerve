Options off set motor powers and culculate the module

option one
motor 1-  vectorLength - rotation
motor 2-  -vectorLength + z + setModuleAngle(vectorAngle, constant.Kp_moduleRotation)



option two
motor1- Range.Clip(vectorLength, -0.75, 0.75)+Range.Clip(setModuleAngle(vectorAngle, constant.Kp_moduleRotation), -0.25,0.25)

motor2- -Range.Clip(vectorLength, -0.75, 0.75)+Range.Clip(setModuleAngle(vectorAngle, constant.Kp_moduleRotation), -0.25,0.25)
