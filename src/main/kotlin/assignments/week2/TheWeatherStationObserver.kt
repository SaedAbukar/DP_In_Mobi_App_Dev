package assignments.week2

// --- Data class for measurements ---
data class WeatherData(val temperature: Float, val humidity:
Float, val pressure: Float)

// --- The Subject (or Observable) ---
interface Subject {
    fun removeObserver(observer: Observer)
    fun registerObserver(observer: Observer)
}

class WeatherStation : Subject{
    private var currentData: WeatherData? = null
    private var observers: MutableSet<Observer> = mutableSetOf()
    // This method is called whenever new weather data is available.
    fun measurementsChanged(newData: WeatherData) {
        this.currentData = newData
        println("WeatherStation: Got new data -> $currentData")
        observers.forEach { it.update(newData) }
    }

    override fun removeObserver(observer: Observer) {
        if (observers.contains(observer)){
            observers.remove(observer)
        }
    }

    override fun registerObserver(observer: Observer) {
        observers.add(observer)
    }
}

// --- The Observers ---
interface Observer{
    fun update(newData: WeatherData)
}

class CurrentConditionsDisplay : Observer{
    var temperature: Float? = null
    var humidity: Float? = null
    fun display() {
        println("CurrentConditionsDisplay: Current condition: ${temperature?: "N/A"}C and ${humidity?: "N/A"}% humidity")
    }

    override fun update(newData: WeatherData) {
        temperature = newData.temperature
        humidity = newData.humidity
        display()
    }
}

class StatisticsDisplay : Observer {
    private val temperatures = mutableListOf<Float>()
    fun display() {
        if (temperatures.isNotEmpty()) {
            println("StatisticsDisplay: Avg temperature: ${temperatures.sum() / temperatures.size}C")
        } else println("StatisticsDisplay: No temperatures")
    }

    override fun update(newData: WeatherData) {
        temperatures.add(newData.temperature)
        display()
    }
}

// --- Main function to run the simulation ---
fun main() {
    // 1. Create the WeatherStation (the subject).
    val weatherStation = WeatherStation()
    // 2. Create the display devices (the observers).
    val currentDisplay = CurrentConditionsDisplay()
    val statsDisplay = StatisticsDisplay()

    weatherStation.registerObserver(currentDisplay)
    weatherStation.registerObserver(statsDisplay)

    println("--- Simulating new measurement ---")
    weatherStation.measurementsChanged(WeatherData(25.0f, 65f,
        1012f))
    println("\n--- Simulating another measurement ---")
    weatherStation.measurementsChanged(WeatherData(27.5f, 70f,
        1011f))
    weatherStation.removeObserver(statsDisplay)
    println("\n--- Simulating a final measurement ---")
    weatherStation.measurementsChanged(WeatherData(26.0f, 90f,
        1013f))
}