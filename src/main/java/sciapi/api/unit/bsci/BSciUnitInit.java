package sciapi.api.unit.bsci;

import sciapi.api.unit.*;

public class BSciUnitInit {
	
	public static void Init(){
		UnitDictionary unitdict = UnitDictionary.instance();
		
		//Base Measurements.
		//Length, L
		unitdict.addBaseMeasurement("L");
		//Mass, M
		unitdict.addBaseMeasurement("M");
		//Time, T
		unitdict.addBaseMeasurement("T");
		
		//Current, A
		unitdict.addBaseMeasurement("A");
		//Temperature
		unitdict.addBaseMeasurement("Temp");
		//Mole
		unitdict.addBaseMeasurement("mol");
		//Brightness, Candela
		unitdict.addBaseMeasurement("cd");
		
		//Measurements.
		//Angle
		unitdict.getMeasurement(new MDimension())
		.setName("Angle").setStdName("rad").setClass(Angle.class);
		
		unitdict.addUnit("Angle", "deg", Math.PI/180.0);
		
		
		//Length
		unitdict.getMeasurement(new MDimension("L"))
		.setName("Length").setStdName("m").setClass(Length.class);
		
		unitdict.addUnit("Length", "km", 1000);
		unitdict.addUnit("Length", "cm", 0.01);
		unitdict.addUnit("Length", "mm", 0.001);
		unitdict.addUnit("Length", "um", 1.0e-6);
		unitdict.addUnit("Length", "nm", 1.0e-9);
		unitdict.addUnit("Length", "pm", 1.0e-12);
		
		unitdict.addUnit("Length", "A", 1.0e-10);
		
		//Area
		unitdict.getMeasurement(new MDimension("L", "L"))
		.setName("Area").setStdName("m2").setClass(Area.class);
		
		unitdict.addUnit("Area", "km2", 1000 * 1000);
		unitdict.addUnit("Area", "cm2", 0.01 * 0.01);
		unitdict.addUnit("Area", "mm2", 0.001 * 0.001);
		unitdict.addUnit("Area", "um2", 1.0e-12);
		unitdict.addUnit("Area", "nm2", 1.0e-18);
		unitdict.addUnit("Area", "pm2", 1.0e-24);
		
		unitdict.addUnit("Area", "are", 100);
		unitdict.addUnit("Area", "ha", 10000);

		//Volume
		unitdict.getMeasurement(new MDimension("L", "L", "L"))
		.setName("Volume").setStdName("m3").setClass(Volume.class);
		
		unitdict.addUnit("Volume", "km3", 1.0e+9);
		unitdict.addUnit("Volume", "cm3", 1.0e-6);
		unitdict.addUnit("Volume", "mm3", 1.0e-9);
		unitdict.addUnit("Volume", "um3", 1.0e-18);
		unitdict.addUnit("Volume", "nm3", 1.0e-27);
		unitdict.addUnit("Volume", "pm3", 1.0e-36);
		

		//Mass
		unitdict.getMeasurement(new MDimension("M"))
		.setName("Mass").setStdName("kg").setClass(Mass.class);
		
		unitdict.addUnit("Mass", "g", 0.001);
		unitdict.addUnit("Mass", "mg", 1.0e-6);
		unitdict.addUnit("Mass", "ug", 1.0e-9);
		unitdict.addUnit("Mass", "t", 1000);
		
		//Density
		unitdict.getMeasurement(new MDimension("M"), new MDimension("L", "L", "L"))
		.setName("Density").setStdName("kg/m3").setClass(Density.class);
		
		unitdict.addUnit("Mass", "g/cm3", 1000);
		unitdict.addUnit("Mass", "g/ml", 1000);
		unitdict.addUnit("Mass", "g/L", 1);
		
	
		//Time
		unitdict.getMeasurement(new MDimension("T"))
		.setName("Time").setStdName("s").setClass(Time.class);
		
		unitdict.addUnit("Time", "ms", 0.001);
		unitdict.addUnit("Time", "us", 1.0e-6);
		unitdict.addUnit("Time", "ns", 1.0e-9);
		
		unitdict.addUnit("Time", "min", 60);
		unitdict.addUnit("Time", "h", 3600);
		
		//Solar Day
		unitdict.addUnit("Time", "day", 3600 * 24);
		
		//Tropical Year
		unitdict.addUnit("Time", "year", 3600 * 24 * 365.2422);
		
		
		//Frequency
		unitdict.getMeasurement(new MDimension(), new MDimension("T"))
		.setName("Frequency").setStdName("Hz").setClass(Frequency.class);
		
		unitdict.addUnit("Frequency", "mHz", 1.0e-3);
		unitdict.addUnit("Frequency", "kHz", 1.0e+3);
		unitdict.addUnit("Frequency", "MHz", 1.0e+6);
		unitdict.addUnit("Frequency", "GHz", 1.0e+9);

		
		//Velocity
		unitdict.getMeasurement(new MDimension("L"), new MDimension("T"))
		.setName("Velocity").setStdName("m/s").setClass(Velocity.class);
		
		unitdict.addUnit("Velocity", "km/s", 1000);
		unitdict.addUnit("Velocity", "cm/s", 0.01);
		unitdict.addUnit("Velocity", "mm/s", 0.001);
		unitdict.addUnit("Velocity", "km/h", 1000.0 / 3600.0);
		
		//Speed of light
		unitdict.addUnit("Velocity", "c", 3.0e+8);

		
		//Acceleration
		unitdict.getMeasurement(new MDimension("L"), new MDimension("T", "T"))
		.setName("Acceleration").setStdName("m/s2").setClass(Acceleration.class);
		
		unitdict.addUnit("Acceleration", "km/s2", 1000);
		unitdict.addUnit("Acceleration", "cm/s2", 0.01);
		
		//Force
		unitdict.getMeasurement(new MDimension("M", "L"), new MDimension("T", "T"))
		.setName("Force").setStdName("N").setClass(Force.class);
		
		unitdict.addUnit("Force", "dyn", 1.0e-5);
		unitdict.addUnit("Force", "kgf", 9.80665);
		
		//Pressure
		unitdict.getMeasurement(new MDimension("M"), new MDimension("L", "T", "T"))
		.setName("Pressure").setStdName("Pa").setClass(Pressure.class);
	
		unitdict.addUnit("Pressure", "hPa", 1.0e+2);
		unitdict.addUnit("Pressure", "kPa", 1.0e+3);
		unitdict.addUnit("Pressure", "MPa", 1.0e+6);
		unitdict.addUnit("Pressure", "atm", 1.01325e+5);
		unitdict.addUnit("Pressure", "bar", 1.0e+5);
		unitdict.addUnit("Pressure", "torr", 133.3224);
		unitdict.addUnit("Pressure", "psi", 6.8948e+3);

		//Energy
		unitdict.getMeasurement(new MDimension("M", "L", "L"), new MDimension("T", "T"))
		.setName("Energy").setStdName("J").setClass(Energy.class);
		
		unitdict.addUnit("Energy", "kJ", 1.0e+3);
		unitdict.addUnit("Energy", "MJ", 1.0e+6);
		unitdict.addUnit("Energy", "erg", 1.0e-7);
		unitdict.addUnit("Energy", "cal", 4.184);
		unitdict.addUnit("Energy", "kcal", 4.184e+3);
		unitdict.addUnit("Energy", "KWh", 3.6e+6);
		unitdict.addUnit("Energy", "eV", 1.60218e-19);
		
		//Power
		unitdict.getMeasurement(new MDimension("M", "L", "L"), new MDimension("T", "T", "T"))
		.setName("Power").setStdName("W").setClass(Power.class);
		
		unitdict.addUnit("Power", "mW", 1.0e-3);
		unitdict.addUnit("Power", "kW", 1.0e+3);
		unitdict.addUnit("Power", "MW", 1.0e+6);
		unitdict.addUnit("Power", "GW", 1.0e+9);
		unitdict.addUnit("Power", "erg/s", 1.0e-7);
		unitdict.addUnit("Power", "hp", 746);
		
		//Angular Momentum
		unitdict.getMeasurement(new MDimension("M", "L", "L"), new MDimension("T"))
		.setName("Momentum").setStdName("Js").setClass(Momentum.class);
		
		unitdict.addUnit("Momentum", "h", 6.626e-34);
		
		//Specific Energy
		unitdict.getMeasurement(new MDimension("L", "L"), new MDimension("T", "T"))
		.setName("Specific Energy").setStdName("J/kg").setClass(SpecificE.class);
		
		unitdict.addUnit("Specific Energy", "J/g", 1.0e+3);
		unitdict.addUnit("Specific Energy", "kJ/kg", 1.0e+3);
		unitdict.addUnit("Specific Energy", "MJ/kg", 1.0e+6);
		unitdict.addUnit("Specific Energy", "cal/g", 4.184e+3);
		unitdict.addUnit("Specific Energy", "kcal/kg", 4.184e+3);
		unitdict.addUnit("Specific Energy", "erg/g", 1.0e-4);
		
		//Energy Density
		unitdict.getMeasurement(new MDimension("M"), new MDimension("L", "T", "T"))
		.setName("Energy Density").setStdName("J/m3").setClass(EDensity.class);
		
		unitdict.addUnit("Energy Density", "J/L", 1.0e+3);
		unitdict.addUnit("Energy Density", "kJ/L", 1.0e+6);
		unitdict.addUnit("Energy Density", "MJ/L", 1.0e+9);
		unitdict.addUnit("Energy Density", "J/cm3", 1.0e+6);
		unitdict.addUnit("Energy Density", "J/ml", 1.0e+6);
		unitdict.addUnit("Energy Density", "kJ/ml", 1.0e+9);
		
		
		//Current
		unitdict.getMeasurement(new MDimension("A"))
		.setName("Current").setStdName("A").setClass(Current.class);
		
		unitdict.addUnit("Current", "mA", 1.0e-3);
		unitdict.addUnit("Current", "uA", 1.0e-6);
		unitdict.addUnit("Current", "kA", 1.0e+3);
		unitdict.addUnit("Current", "MA", 1.0e+6);
		
		//Electric Charge
		unitdict.getMeasurement(new MDimension("A", "T"))
		.setName("ElCharge").setStdName("C").setClass(ElCharge.class);
		
		unitdict.addUnit("ElCharge", "mC", 1.0e-3);
		unitdict.addUnit("ElCharge", "uC", 1.0e-6);
		unitdict.addUnit("ElCharge", "nC", 1.0e-9);
		unitdict.addUnit("ElCharge", "pC", 1.0e-12);
		unitdict.addUnit("ElCharge", "kC", 1.0e+3);
		
		//Voltage
		unitdict.getMeasurement(new MDimension("M", "L", "L"), new MDimension("T", "T", "T", "A"))
		.setName("Voltage").setStdName("V").setClass(Voltage.class);
		
		unitdict.addUnit("Voltage", "mV", 1.0e-3);
		unitdict.addUnit("Voltage", "uV", 1.0e-6);
		unitdict.addUnit("Voltage", "kV", 1.0e+3);
		unitdict.addUnit("Voltage", "MV", 1.0e+6);
		unitdict.addUnit("Voltage", "GV", 1.0e+9);

		
		//Temperature
		unitdict.getMeasurement(new MDimension("Temp"))
		.setName("Temperature").setStdName("K").setClass(Temperature.class);
		
		unitdict.addUnit("Temperature", "C", 273.15, 1.0);
		unitdict.addUnit("Temperature", "F", 255.37, 5.0/9.0);
		
		//Heat Capacity
		unitdict.getMeasurement(new MDimension("M", "L", "L"), new MDimension("T", "T", "Temp"))
		.setName("Heat Capacity").setStdName("J/K").setClass(HeatCapacity.class);
		
		unitdict.addUnit("Heat Capacity", "kJ/K", 1.0e+3);
		unitdict.addUnit("Heat Capacity", "MJ/K", 1.0e+6);
		unitdict.addUnit("Heat Capacity", "cal/K", 4.184);
		unitdict.addUnit("Heat Capacity", "kcal/K", 4.184e+3);

		//Specific Heat
		unitdict.getMeasurement(new MDimension("L", "L"), new MDimension("T", "T", "Temp"))
		.setName("Specific Heat").setStdName("J/kgK").setClass(SpecificE.class);
		
		unitdict.addUnit("Specific Heat", "J/gK", 1.0e+3);
		unitdict.addUnit("Specific Heat", "kJ/gK", 1.0e+6);
		unitdict.addUnit("Specific Heat", "cal/gK", 4.184e+3);
		unitdict.addUnit("Specific Heat", "kcal/gK", 4.184e+6);
		
		
		//Init Constants.
		BSciConstants.Init();
	}

}
