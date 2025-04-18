package d.stoplist_be.domain.persona.controller;

import d.stoplist_be.domain.persona.PersonaListResponseDto;
import d.stoplist_be.domain.persona.SelectPersonaRequestDto;
import d.stoplist_be.domain.persona.UserInfoResponseDto;
import d.stoplist_be.domain.persona.service.PersonaService;
import d.stoplist_be.global.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PersonaController {

    private PersonaService personaService;

    PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/personalist")
    public ApiResponse<PersonaListResponseDto> getPersonas() {
        return new ApiResponse<>(200, "ok", personaService.getPersonaList());
    }

    @GetMapping("/user-info")
    public ApiResponse<UserInfoResponseDto> getUserInfo(@RequestParam Long userId) {
        return new ApiResponse<>(200, "ok", personaService.searchPersona(userId));
    }

    @PostMapping("/persona")
    public ApiResponse<?> selectPersona(@RequestBody SelectPersonaRequestDto requestDto) {
        return new ApiResponse<>(200, "ok", personaService.updatePersona(requestDto.userId(), requestDto.personaName()));
    }
}
