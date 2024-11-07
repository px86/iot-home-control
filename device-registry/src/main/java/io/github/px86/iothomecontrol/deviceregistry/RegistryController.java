package io.github.px86.iothomecontrol.deviceregistry;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class RegistryController {

  @Autowired private RegistryService registryService;

  @GetMapping("/registry")
  public Map<String, String> registry() {
    return this.registryService.getAll();
  }
}
