package d.stoplist_be.domain.persona;

import lombok.Data;

@Data
public class PersonaInfo {

    private Long personaId;
    private String personaName;
    private boolean flag;

    public PersonaInfo(Long personaId, String personaName, boolean flag) {
        this.personaId = personaId;
        this.personaName = personaName;
        this.flag = flag;
    }
}
