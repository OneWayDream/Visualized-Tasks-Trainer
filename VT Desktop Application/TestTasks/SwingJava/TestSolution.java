import wrappers.*;
import ru.itis.visualtasks.desktopapp.application.entities.project.SolutionScheme;


public class TestSolution extends SolutionScheme{

    private CountDownLatch startCountDownLatch = new CountDownLatch(5);

    @Override
    public void execute() {
        try{
            // add the first car to the race
            CarRegistration firstCarRegistration = new CarRegistration(1, 20);
            firstCarRegistration.driveToTheRace();

            Thread.sleep(1000);

            // add the second one
            CarRegistration secondCarRegistration = new CarRegistration(2, 25);
            secondCarRegistration.driveToTheRace();

            //count to start
            Thread.sleep(1000);
            System.out.println("Ready!");
            startCountDownLatch.countDown();
            Thread.sleep(1000);
            System.out.println("Steady!");
            startCountDownLatch.countDown();
            Thread.sleep(1000);
            System.out.println("Go!");
            startCountDownLatch.countDown();
            
        } catch (InterruptedException e) {
            //ignore
        }
    }

    public class CarRegistration {

        private final int carNumber;
        private final int carSpeed;
        private final Car car;

        public CarRegistration(int carNumber, int carSpeed){
            this.carNumber = carNumber;
            this.carSpeed = carSpeed;
            car = new Car(carNumber, carSpeed, this::carActions);
        }

        private void carActions(){
            try {
                System.out.printf("Car №%d arrived at the starting line.\n", carNumber);
                car.addToTheRace();
                startCountDownLatch.countDown();
                startCountDownLatch.await();
                Thread.sleep((100 / carSpeed) * 1000);
                System.out.printf("Car №%d has finished!\n", carNumber);
            } catch (InterruptedException exception) {
                //ignore
            }
        }

        public void driveToTheRace(){
            car.driveToTheRace();
        }

    }

}
