package br.com.gerafit.config;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;

//Esta classe foi criada para configurar os beans.

@FacesConfig(version=Version.JSF_2_3)//Anotacao referindo a versão do JSF.
@ApplicationScoped//Faz a classe ser reconhecida no servidor.
public class Config {

}
