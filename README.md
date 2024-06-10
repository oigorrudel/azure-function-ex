# azure-functions-ex

### Steps
1 - Instalar plugin do <i><b>Azure Toolkit</b></i> no ```IntelliJ``` <br>
2 - Instalar <i><b>Azure Functions Core Tools</b></i> (ajustar no ```IntelliJ``` o folder da instalação) <br>
3 - Rodar <i><b>Azurite</b></i> do ```docker-composer.yml``` <br>
4 - Setar ```JAVA_HOME``` nas ENV's do run <br>
5 - Rodar a function


### URL's
    - OBS: Porta é configurada no RUN do IntelliJ

 - Listar functions: GET ```http://localhost:<port>/admin/functions```
 - Disparar function: POST ```http://localhost:<port>/admin/functions/:function```

![img.png](img.png)