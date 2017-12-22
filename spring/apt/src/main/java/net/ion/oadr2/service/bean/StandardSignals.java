/*
 * Copyright 2014 kt corp. All rights reserved.
 * This is a proprietary software of kt corp, and you may not use this file except in 
 * compliance with license agreement with kt corp. Any redistribution or use of this 
 * software, with or without modification shall be strictly prohibited without prior written 
 * approval of kt corp, and the copyright notice above does not evidence any actual or 
 * intended publication of such software.
 */ 


package net.ion.oadr2.service.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.ion.open.oadr2.model.SignalTypeEnumeratedType;


public class StandardSignals {
	//signal name
	public static String SIMPLE = "SIMPLE";
	public static String ELECTRICITY_PRICE = "ELECTRICITY_PRICE";
	public static String ENERGY_PRICE = "ENERGY_PRICE";
	public static String DEMAND_CHARGE = "DEMAND_CHARGE";
	public static String BID_PRICE = "BID_PRICE";
	public static String BID_LOAD = "BID_LOAD";
	public static String BID_ENERGY = "BID_ENERGY";
	public static String CHARGE_STATE = "CHARGE_STATE";
	public static String LOAD_DISPATCH = "LOAD_DISPATCH";
	public static String LOAD_CONTROL = "LOAD_CONTROL";

	public static enum UNIT{
    	none,
    	currency,
    	currencyPerKW,
    	currencyPerKWh,
    	currencyPerTherm,
    	current,
    	frequency,
    	energyApparent,
    	energyReactive,
    	energyReal,
    	powerApparent,
    	powerReactive,
    	powerReal,
    	pulseCount,
    	temperature,
    	therms,
    	voltage
    }

	private static Map<String, Map<String, List<String>>> signalUnitMap = new HashMap<String, Map<String, List<String>>>();

	static {
		Map<String, List<String>> typeMap = new HashMap<String, List<String>>();

		// SIMPLE
		List<String> units = new ArrayList<String>();
		units.add(UNIT.none.name());

		typeMap.put(SignalTypeEnumeratedType.LEVEL.value(), units);
//		typeMap.put(SignalTypeEnumeratedType.DELTA.value(), units);
//		typeMap.put(SignalTypeEnumeratedType.MULTIPLIER.value(), units);
//		typeMap.put(SignalTypeEnumeratedType.PRICE.value(), units);
//		typeMap.put(SignalTypeEnumeratedType.PRICE_MULTIPLIER.value(), units);
//		typeMap.put(SignalTypeEnumeratedType.PRICE_RELATIVE.value(), units);
//		typeMap.put(SignalTypeEnumeratedType.PRODUCT.value(), units);
//		typeMap.put(SignalTypeEnumeratedType.SETPOINT.value(), units);

		signalUnitMap.put(SIMPLE, typeMap);

		// ELECTRICITY_PRICE
		typeMap = new HashMap<String, List<String>>();
		units = new ArrayList<String>();
		units.add(UNIT.currencyPerKWh.name());

		typeMap.put(SignalTypeEnumeratedType.PRICE.value(), units);
		typeMap.put(SignalTypeEnumeratedType.PRICE_RELATIVE.value(), units);

		units = new ArrayList<String>();
		units.add(UNIT.none.name());
		typeMap.put(SignalTypeEnumeratedType.PRICE_MULTIPLIER.value(), units);

		signalUnitMap.put(ELECTRICITY_PRICE, typeMap);

		// LOAD_DISPATCH
		typeMap = new HashMap<String, List<String>>();
		units = new ArrayList<String>();
		units.add(UNIT.powerApparent.name());
		units.add(UNIT.powerReactive.name());
		units.add(UNIT.powerReal.name());

		typeMap.put(SignalTypeEnumeratedType.LEVEL.value(), units);
		typeMap.put(SignalTypeEnumeratedType.SETPOINT.value(), units);
		typeMap.put(SignalTypeEnumeratedType.DELTA.value(), units);

		units = new ArrayList<String>();
		units.add(UNIT.none.name());
		typeMap.put(SignalTypeEnumeratedType.MULTIPLIER.value(), units);

		signalUnitMap.put(LOAD_DISPATCH, typeMap);

		// ENERGY_PRICE
		typeMap = new HashMap<String, List<String>>();
		units = new ArrayList<String>();
		units.add(UNIT.currencyPerKWh.name());

		typeMap.put(SignalTypeEnumeratedType.PRICE.value(), units);
		typeMap.put(SignalTypeEnumeratedType.PRICE_RELATIVE.value(), units);

		units = new ArrayList<String>();
		units.add(UNIT.none.name());
		typeMap.put(SignalTypeEnumeratedType.PRICE_MULTIPLIER.value(), units);

		signalUnitMap.put(ENERGY_PRICE, typeMap);

		// DEMAND_CHARGE
		typeMap = new HashMap<String, List<String>>();
		units = new ArrayList<String>();
		units.add(UNIT.currencyPerKW.name());

		typeMap.put(SignalTypeEnumeratedType.PRICE.value(), units);
		typeMap.put(SignalTypeEnumeratedType.PRICE_RELATIVE.value(), units);

		units = new ArrayList<String>();
		units.add(UNIT.none.name());
		typeMap.put(SignalTypeEnumeratedType.PRICE_MULTIPLIER.value(), units);

		signalUnitMap.put(DEMAND_CHARGE, typeMap);

		// BID_PRICE
		typeMap = new HashMap<String, List<String>>();
		units = new ArrayList<String>();
		units.add(UNIT.currencyPerKW.name());
		units.add(UNIT.currencyPerKWh.name());
		units.add(UNIT.currencyPerTherm.name());

		typeMap.put(SignalTypeEnumeratedType.PRICE.value(), units);

		signalUnitMap.put(BID_PRICE, typeMap);

		// BID_LOAD
		typeMap = new HashMap<String, List<String>>();
		units = new ArrayList<String>();
		units.add(UNIT.powerApparent.name());
		units.add(UNIT.powerReactive.name());
		units.add(UNIT.powerReal.name());

		typeMap.put(SignalTypeEnumeratedType.SETPOINT.value(), units);

		signalUnitMap.put(BID_LOAD, typeMap);

		// BID_ENERGY
		typeMap = new HashMap<String, List<String>>();
		units = new ArrayList<String>();
		units.add(UNIT.energyApparent.name());
		units.add(UNIT.energyReactive.name());
		units.add(UNIT.energyReal.name());

		typeMap.put(SignalTypeEnumeratedType.SETPOINT.value(), units);

		signalUnitMap.put(BID_ENERGY, typeMap);

		// CHARGE_STATE
		typeMap = new HashMap<String, List<String>>();
		units = new ArrayList<String>();
		units.add(UNIT.energyApparent.name());
		units.add(UNIT.energyReactive.name());
		units.add(UNIT.energyReal.name());

		typeMap.put(SignalTypeEnumeratedType.SETPOINT.value(), units);
		typeMap.put(SignalTypeEnumeratedType.DELTA.value(), units);

		units = new ArrayList<String>();
		units.add(UNIT.none.name());
		typeMap.put(SignalTypeEnumeratedType.MULTIPLIER.value(), units);

		signalUnitMap.put(CHARGE_STATE, typeMap);

		// LOAD_CONTROL
		typeMap = new HashMap<String, List<String>>();
		units = new ArrayList<String>();
		units.add(UNIT.none.name());

		typeMap.put(net.ion.open.oadr2.model.v20b.ei.SignalTypeEnumeratedType.X_LOAD_CONTROL_CAPACITY.value(), units);
		typeMap.put(net.ion.open.oadr2.model.v20b.ei.SignalTypeEnumeratedType.X_LOAD_CONTROL_LEVEL_OFFSET.value(), units);
		typeMap.put(net.ion.open.oadr2.model.v20b.ei.SignalTypeEnumeratedType.X_LOAD_CONTROL_SETPOINT.value(), units);
		typeMap.put(net.ion.open.oadr2.model.v20b.ei.SignalTypeEnumeratedType.X_LOAD_CONTROL_PERCENT_OFFSET.value(), units);

		signalUnitMap.put(LOAD_CONTROL, typeMap);

	}


	private StandardSignals(){

	}

	public static List<String> getSignalNames(){
		Iterator<String> iterator = signalUnitMap.keySet().iterator();

		List<String> signalNames = new ArrayList<String>();
		while(iterator.hasNext()){
			signalNames.add(iterator.next());
		}
		return signalNames;
	}

	public static List<String> getSignalTypes(String signalName){
		Map<String, List<String>> typeMap = signalUnitMap.get(signalName);

		List<String> signalTypes = new ArrayList<String>();
		Iterator<String> iterator = typeMap.keySet().iterator();
		while(iterator.hasNext()){
			signalTypes.add(iterator.next());
		}
		return signalTypes;
	}

	public static Map<String, List<String>> getSignalMap(String signalName){
		return signalUnitMap.get(signalName);
	}

	public static List<String> getSignalTypeUnits(String signalName, String typeName){
		return signalUnitMap.get(signalName).get(typeName);
	}

}