package com.example.todoapp

class FoodOrder private constructor(
    val bread: String?,
    val condiments: String?,
    val meat: String?,
    val fish: String?,
    val dish: String?
) {

    data class Builder(
        var bread: String? = null,
        var condiments: String? = null,
        var meat: String? = null,
        var fish: String? = null,
        var dish: String? = null
    ) {

        fun bread(bread: String) = apply { this.bread = bread }
        fun condiments(condiments: String) = apply { this.condiments = condiments }
        fun meat(meat: String) = apply { this.meat = meat }
        fun fish(fish: String) = apply { this.fish = fish }
        fun dish(dish: String) = apply { this.dish = dish }
        fun build() = FoodOrder(bread, condiments, meat, fish, dish)
    }
}

val foodOrder = FoodOrder.Builder()
    .fish("dwodkq")
    .bread("das;ldma")
    .dish("fvjjhj")
    .build()
val foof = FoodOrder.Builder()
    .apply {

    }

/*
builder pattern and Factory both create objects for us and improve readability

Builder : return an instance of complex object || inner class involved

 Factory : return multiple constructors || subclass involved



 */
interface Virus {
    fun mutate()
    fun spread() {
        println("Spreading the virus...")
    }
}

class CoronaVirus : Virus {
    override fun mutate() {
        println("Mutating the corona virus...")
    }
}

class InfluenzaVirus : Virus {
    override fun mutate() {
        println("Mutating the flu virus...")
    }
}

class HIVVirus : Virus {
    override fun mutate() {
        println("Mutating the HIV virus...")
    }
}

enum class VirusType {
    CORONA_VIRUS, INFLUENZA, HIV
}

class VirusFactory {
    fun makeVirus(type: VirusType): Virus? {
        return when (type) {
            VirusType.CORONA_VIRUS -> CoronaVirus()
            VirusType.HIV -> HIVVirus()
            VirusType.INFLUENZA -> InfluenzaVirus()
            else -> null
        }
    }
}