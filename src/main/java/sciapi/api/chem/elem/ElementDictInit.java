package sciapi.api.chem.elem;

public class ElementDictInit {
	public static void Init(ElementDictionary dic){
		ElementSet chemset = dic.newElementSet(dic.chemical, 200);
		
		chemset.getElement(1).setName("Hydrogen").setSymbol("H")
		.setIsotope("Deuterium", "D", 2, 1.0e-4f)
		.setIsotope("Tritium", "T", 3, 0)
		.setIsotopeasRest("Hydrogen",  "H", 1);
		
		chemset.getElement(2).setName("Helium").setSymbol("He")
		.setIsotope(3, 1.4e-6f)
		.setIsotopeasRest(4);
		
		chemset.getElement(3).setName("Lithium").setSymbol("Li")
		.setIsotope(6, 7.5e-2f)
		.setIsotopeasRest(7);
		
		chemset.getElement(4).setName("Beryllium").setSymbol("Be")
		.setIsotopeasRest(9);
		
		chemset.getElement(5).setName("Boron").setSymbol("B")
		.setIsotope(10, 0.190f)
		.setIsotopeasRest(11);
		
		chemset.getElement(6).setName("Carbon").setSymbol("C")
		.setIsotope(12, 0.9893f)
		.setIsotopeasRest(13);
		
		chemset.getElement(7).setName("Nitrogen").setSymbol("N")
		.setIsotope(15, 0.00364f)
		.setIsotopeasRest(14);
		
		chemset.getElement(8).setName("Oxygen").setSymbol("O")
		.setIsotope(17, 3.8e-4f)
		.setIsotope(18, 2.05e-3f)
		.setIsotopeasRest(16);
		
		chemset.getElement(9).setName("Fluorine").setSymbol("F")
		.setIsotopeasRest(19);
		
		chemset.getElement(10).setName("Neon").setSymbol("Ne")
		.setIsotope(21, 0.0027f)
		.setIsotope(22, 0.0925f)
		.setIsotopeasRest(20);
		
		chemset.getElement(11).setName("Sodium").setSymbol("Na")
		.setIsotopeasRest(23);
		
		chemset.getElement(12).setName("Magnesium").setSymbol("Mg")
		.setIsotope(24, 0.7899f)
		.setIsotope(25, 0.1000f)
		.setIsotopeasRest(26);
		
		chemset.getElement(13).setName("Aluminium").setSymbol("Al")
		.setIsotopeasRest(27);
		
		chemset.getElement(14).setName("Silicon").setSymbol("Si")
		.setIsotope(29, 0.04685f)
		.setIsotope(30, 0.03092f)
		.setIsotopeasRest(28);
		
		chemset.getElement(15).setName("Phosphorus").setSymbol("P")
		.setIsotopeasRest(31);
		
		chemset.getElement(16).setName("Sulfur").setSymbol("S")
		.setIsotope(33, 0.0076f)
		.setIsotope(34, 0.0429f)
		.setIsotope(36, 2e-4f)
		.setIsotopeasRest(32);
		
		chemset.getElement(17).setName("Chlorine").setSymbol("Cl")
		.setIsotope(35, 0.7576f)
		.setIsotopeasRest(37);
		
		chemset.getElement(18).setName("Argon").setSymbol("Ar")
		.setIsotope(36, 0.003336f)
		.setIsotope(38, 6.29e-4f)
		.setIsotopeasRest(40);
		
		chemset.getElement(19).setName("Potassium").setSymbol("K")
		.setIsotope(41, 0.067302f)
		.setIsotopeasRest(39);
		
		chemset.getElement(20).setName("Calcium").setSymbol("Ca")
		.setIsotope(42, 0.00647f)
		.setIsotope(43, 0.00135f)
		.setIsotope(44, 0.02086f)
		.setIsotope(48, 0.00187f)
		.setIsotopeasRest(40);
		
		chemset.getElement(21).setName("Scandium").setSymbol("Sc")
		.setIsotopeasRest(45);
	}
}
