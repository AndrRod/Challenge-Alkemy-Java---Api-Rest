package com.Alkemy.Challenge.Java;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class ChallengeJavaApplicationTests {

	Calculadora underTest = new Calculadora();

//	Anotación test es Junit test
	@Test
	void debeAgregar2Nums() {
//		given
		int numOne = 20;
		int numTwo = 30;
//		when
		int resultado = underTest.add(numOne, numTwo);
//		then
		int resultadoEsperado = 50;
		assertThat(resultado).isEqualTo(resultadoEsperado);
	}

	class Calculadora{
		int add(int a, int b){return a + b;}
	}
}
