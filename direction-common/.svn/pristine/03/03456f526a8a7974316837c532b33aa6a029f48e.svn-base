package com.direction.common.utils.io;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

/**
 * 配置文件加载（Boot）.
 * 
 * <pre>
 * 	配置文件加载（Boot）
 * </pre>
 * 
 * @author liutao
 * @since $Rev$
 *
 */
public class PropertyLoader implements org.springframework.boot.env.PropertySourceLoader {

	private static boolean isLoadPropertySource = false;

	@Override
	public String[] getFileExtensions() {

		return new String[] {
		  "properties",
		  "yml"
		};
	}

	@Override
	public List<PropertySource<?>> load(String name, Resource resource) throws IOException {

		if (!isLoadPropertySource) {
			isLoadPropertySource = true;
			Properties properties = PropertiesUtils.getInstance().getProperties();
			return Collections.singletonList(new OriginTrackedMapPropertySource("application", properties));
		}
		return Collections.emptyList();
	}

}
