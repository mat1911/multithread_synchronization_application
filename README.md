# Application supporting scrum methodology

## Brief description

The purpose of the application is to visualise the problem of the producer and the consumer. The production lines create products from two ingredients located in two different storehouses. The finished products go to an output storehouse, from which they are exported after some time. Access to the different resources is protected by semaphores.

## Configuration

To change the animation parameters, change the values of the fields in the Initializer class.

```Java
    private final int FIRST_STOREHOUSE_CAPACITY = 40; //MAX 40
    private final int SECOND_STOREHOUSE_CAPACITY = 40; // MAX 40
    private final int OUTPUT_STOREHOUSE_CAPACITY = 21; // MAX 21
    private final int NUMBER_OF_PRODUCTION_LINES = 9; //MAX 9
    private final int CAPACITY_OF_CAR = 16;
    private final int NUMBER_OF_EXPORTED_PRODUCTS = 6;
```

## Screenshots

Standard situation - production runs fine.

![User projects](https://drive.google.com/uc?export=view&id=1oTaUooVK4oYSAtbWj_kWStkNB94-IWo_)

<br />

Export warehouse is too slow - production lines are blocked.

![User projects](https://drive.google.com/uc?export=view&id=1NmTKp61e1XbFO7Zq4F96FOeUHjmDSUiJ)
