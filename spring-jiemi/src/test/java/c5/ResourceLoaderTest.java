package c5;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

public class ResourceLoaderTest {
    @Test
    public void test_DefaultResourceLoader() {
        ResourceLoader loader = new DefaultResourceLoader();
        Resource fakeFileResource = loader.getResource("/Users/my/IdeaProjects/spring-playground/spring-jiemi/README.md");
        System.out.println(fakeFileResource instanceof ClassPathResource);

        Resource urlResource1 = loader.getResource("file:/Users/my/IdeaProjects/spring-playground/spring-jiemi/README.md");
        System.out.println(urlResource1 instanceof UrlResource);

        Resource urlResource2 = loader.getResource("https://www.baidu.com");
        System.out.println(urlResource2 instanceof UrlResource);
    }

    @Test
    public void test_FileSystemResourceLoader() {
        ResourceLoader loader = new FileSystemResourceLoader();
        Resource urlResource1 = loader.getResource("D:/Users/my/IdeaProjects/spring-playground/spring-jiemi/README.md");
        System.out.println(urlResource1 instanceof UrlResource);
        System.out.println(urlResource1 instanceof FileSystemResource);
    }

    @Test
    public void get_resources_using_PathMatchingResourcePatternResolver() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // resolver 可以直接加载项目中的资源
        Resource[] res = resolver.getResources("classpath*:beans/*");
        for (Resource re : res) {
            System.out.println(re.getFilename());
        }

        // 也可以加载 spring jar 包中的资源
        System.out.println("-------------> source in jar");
        res = resolver.getResources("classpath*:org/springframework/core/io/support/*");
        for (Resource re : res) {
            System.out.println(re.getFilename());
        }
    }
}
