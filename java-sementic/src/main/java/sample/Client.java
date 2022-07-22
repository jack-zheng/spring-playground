package sample;

public class Client {
    public void doFilter() throws Exception {
        try(SimpleAutoCloseable auto = new SimpleAutoCloseable("Request")) {
            System.out.println("special filter logic...");
            System.out.println("filterChain.doFilter(request, response)");
        }
    }
}
