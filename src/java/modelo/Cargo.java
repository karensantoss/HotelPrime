package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author otacilio_gomes
 */
@Entity
public class Cargo implements Serializable {

    @Id
    @GeneratedValue
    private int codigo;
    private String nome;
    @Lob
    private String principaisResponsabilidads;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPrincipaisResponsabilidads() {
        return principaisResponsabilidads;
    }

    public void setPrincipaisResponsabilidads(String principaisResponsabilidads) {
        this.principaisResponsabilidads = principaisResponsabilidads;
    }
    
}
