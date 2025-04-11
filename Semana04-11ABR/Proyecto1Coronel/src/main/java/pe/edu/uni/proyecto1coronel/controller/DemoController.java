package pe.edu.uni.proyecto1coronel.controller;

import pe.edu.uni.proyecto1coronel.dto.DemoDto;
import pe.edu.uni.proyecto1coronel.service.DemoService;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog www.desarrollasoftware.com
 * @email gcoronelc@gmail.com
 * @youtube www.youtube.com/DesarrollaSoftware
 * @facebook www.facebook.com/groups/desarrollasoftware/
 * @cursos gcoronelc.github.io
 */
public class DemoController {

	private DemoService demoService;

	public DemoController() {
		demoService = new DemoService();
	}

	public DemoDto procesar(DemoDto bean) {
		return demoService.procesar(bean);
	}

}
