package automata;

public class Automata {

    boolean resultado;
    int numEstados;
    int[] Q;
    int tamañoAlfabeto;
    int[][] Delta;
    int estadoInicial;
    int cantEstadosAceptacion;
    int[] EstadoAceptacion;
    int[] intCadenaE;
    int[] Sigma;
    
    public static void main(String[] args) {

        Automata au = new Automata();
        au.introducirValores("C:\\Users\\ALANZON\\Desktop\\Automata\\src\\automata\\MiConfiguracion.txt", "C:\\Users\\ALANZON\\Desktop\\Automata\\src\\automata\\cadena.txt");
        au.evaluar();
        
       if (au.resultado) {
            System.out.println("");
            System.out.println("Cadena Aceptada");
        } else {
            System.out.println("");
            System.out.println("Cadena Rechazada");
        }
        //System.out.println(intCadenaE[9]);
    }
    
    String contCadena;
    public void introducirValores(String Archivo, String Cadena) {

        archivo a = new archivo();
        String contArchivo = a.leerArchivo(Archivo);
        String[] arrayArchivo = contArchivo.split(",");
        int[] arregloArchivo = new int[arrayArchivo.length];

        for (int i = 0; i < arrayArchivo.length; i++) {
            arregloArchivo[i] = Integer.parseInt(arrayArchivo[i]);
        }
        
        System.out.print("Elementos de configuracion: ");
        for (int i = 0; i < arrayArchivo.length; i++) {
            System.out.print(arregloArchivo[i]+",");
        }
        
        numEstados = arregloArchivo[0];
        Q = new int[numEstados];

        for (int i = 0; i < numEstados; i++) {

            Q[i] = arregloArchivo[i + 1];
        }
        
        System.out.println("");
        System.out.print("Los estados son: ");
        for (int i = 0; i < numEstados; i++) {

            System.out.print(Q[i]+",");
        }
        
        //obtener los valores de la matriz de identidad
        tamañoAlfabeto = arregloArchivo[numEstados + 1];
        Delta = new int[numEstados][tamañoAlfabeto];
        int temp = 0;
        for (int i = 0; i < numEstados; i++) {
            for (int j = 0; j < tamañoAlfabeto; j++) {
                int primerValor = Q.length + 2;
                Delta[i][j] = arregloArchivo[primerValor + temp];
                temp++;
            }
        }
        
        System.out.println("");
        System.out.print("La matriz de entidad es: ");
        for (int i = 0; i < numEstados; i++) {
            for (int j = 0; j < tamañoAlfabeto; j++) {
                System.out.print(Delta[i][j]+","); 
            }
        }

        estadoInicial = arregloArchivo[Q.length + 2 + temp];
        System.out.println("");
        System.out.println("El estado inicial es:"+estadoInicial);

        cantEstadosAceptacion = arregloArchivo[Q.length + 2 + temp + 1];
        System.out.println("La cant de estados de aceptacion son: "+cantEstadosAceptacion);

        EstadoAceptacion = new int[cantEstadosAceptacion];
        for(int i=0;i<cantEstadosAceptacion;i++){
            EstadoAceptacion[i] = arregloArchivo[Q.length + 4 + temp + i];
        }
        
        System.out.print("");
        System.out.print("Los estados de aceptacion son: ");
        for(int i=0;i<cantEstadosAceptacion;i++){
            System.out.print(EstadoAceptacion[i]+",");
        }
        
        Sigma = new int[tamañoAlfabeto];
        
        for(int i=0;i<tamañoAlfabeto;i++){
            Sigma[i] = arregloArchivo[Q.length + 5 + temp + i];
        }
        
        System.out.println("");
        System.out.print("Los valores de sigma son: ");
        for(int i=0;i<tamañoAlfabeto;i++){
            System.out.print(Sigma[i]+",");
        }
        
        
        contCadena = a.leerArchivo(Cadena);

        intCadenaE = new int[contCadena.length()];
        int i = 0;

        for (char valor : contCadena.toCharArray()) {
            intCadenaE[i] = Integer.valueOf(valor) - 48;
            i++;
        }
         
        System.out.println("");
        System.out.print("Los valores de entrada son: ");
        for (i=0;i<intCadenaE.length;i++){
            System.out.print(intCadenaE[i]);
        }
        
    }
    
    
    public void evaluar() 
    {  
        int estadoActual = estadoInicial;
        int siguienteValor = siguientevalor();
        
        int i=0;
        while(i<intCadenaE.length)
        {
            estadoActual = transicion(estadoActual,siguienteValor);
            siguienteValor = siguientevalor();
        i++;
        }
        
        for(i=0;i<EstadoAceptacion.length;i++)
        {
            if(estadoActual==EstadoAceptacion[i])
            {
            resultado=true;
            }
            else
            {
            resultado=false;
            }
        }    
    }
    
    int indice=0;
    int valorCadenaA = 0;
    public int siguientevalor()
    {
        if(indice<intCadenaE.length)
        {
             valorCadenaA=intCadenaE[indice];
             indice++;
        }
       return valorCadenaA;  
    }
    
    public int transicion(int estadoActual,int siguienteValor)
    {
        int estadoActualQ=0;
        int elementoEntradaA=0;
        
        for(int i=0;i<Q.length;i++)
        {
            if(estadoActual==Q[i])
            {
                estadoActualQ = i;
            }
        }
        for(int j=0;j<Sigma.length;j++)
        {
            if(siguienteValor==Sigma[j])
            {
                elementoEntradaA=j;
            }
        }
        
        estadoActual=Delta[estadoActualQ][elementoEntradaA];
        
        return estadoActual;
    }
    
}
