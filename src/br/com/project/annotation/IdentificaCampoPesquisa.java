package br.com.project.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)//m�quina virtual
@Documented
public abstract @interface IdentificaCampoPesquisa {
	
	String descricaoCampo();//descri��o do campo para a tela
	String campoConsulta();//campo do banco de dados
	int principal() default 10000;//posi��o que ir� aparecer no combo da tela
}
