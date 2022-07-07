package bean;

public class DefaultServiceLocator {
    private static ClientService clientService = ClientService.createInstance();

    public ClientService createClientServiceInstance() {
        return clientService;
    }
}
