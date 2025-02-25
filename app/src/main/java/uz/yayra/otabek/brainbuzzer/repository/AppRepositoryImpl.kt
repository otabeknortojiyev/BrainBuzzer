package uz.yayra.otabek.brainbuzzer.repository

import uz.yayra.otabek.brainbuzzer.R
import uz.yayra.otabek.brainbuzzer.data.model.GenreData
import uz.yayra.otabek.brainbuzzer.data.model.QuizData
import uz.yayra.otabek.brainbuzzer.data.sharedpreference.LocalStorage
import uz.yayra.otabek.brainbuzzer.utils.GenreEnum
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val storage: LocalStorage) : AppRepository {
    override fun saveName(name: String) {
        storage.name = name
    }

    override fun getName() = storage.name
    override fun getMoney() = storage.money
    override fun saveMoney(money: Int) {
        storage.money = (storage.money.toInt() + money).toString()
    }

    override fun getQuizGenres(): List<GenreData> {
        return arrayListOf(
            GenreData("Sport Quiz", 20, R.drawable.sport, GenreEnum.SPORT),
            GenreData("Science Quiz", 10, R.drawable.science, GenreEnum.SCIENCE),
            /*GenreData("Math Quiz", 12, R.drawable.math, GenreEnum.MATH),
            GenreData("Animal Quiz", 10, R.drawable.animal, GenreEnum.ANIMAL),
            GenreData("Capital Quiz", 16, R.drawable.capital, GenreEnum.CAPITAL)*/
        )
    }

    override fun getSportQuizzes(): List<QuizData> {
        return arrayListOf(
            QuizData(R.drawable.sport1, "How many players on a football team are on the field at one time?", "10", "11", "12", 2),
            QuizData(R.drawable.sport2, "What is the national sport in Japan?", "Karate", "Judo", "Sumo", 3),
            QuizData(
                R.drawable.sport3,
                "Which athlete was the first to run a marathon under 2 hours?",
                "Osama Al-Suhaimi",
                "Eliud Kipchoge",
                "Haile Gebrselassie",
                2
            ),
            QuizData(R.drawable.sport4, "How many points are awarded for a 3-point shot in basketball?", "1", "2", "3", 3),
            QuizData(
                R.drawable.sport5,
                "What is the most powerful shot in tennis?",
                "Forehand",
                "Backhand",
                "Hey",
                1
            ),
            QuizData(R.drawable.sport6, "What American sport uses the expression \"hat trick\"?", "American football", "Hockey", "Baseball", 2),
            QuizData(
                R.drawable.sport7,
                "What is the name of the most prestigious motorsport championship in the world?",
                "NASCAR",
                "Formula 1",
                "MotoGP",
                2
            ),
            QuizData(R.drawable.sport8, "In which sport can you earn a Green Jacket?", "Golf", "Fencing", "Boxing", 1),
            QuizData(R.drawable.sport5, "What sport is associated with Wimbledon?", "Cricket", "Tennis", "Football", 2),
            QuizData(R.drawable.sport10, "How many minutes does a handball match last?", "40 minutes", "50 minutes", "60 minutes", 3)
        )
    }

    override fun getScienceQuizzes(): List<QuizData> {
        return arrayListOf(
            QuizData(R.drawable.science1, "Which planet is the hottest in the solar system?", "Venus", "Mercury", "Mars", 1),
            QuizData(R.drawable.science1, "Who invented the theory of relativity?", "Isaac Newton", "Nikola Tesla", "Albert Einstein", 3),
            QuizData(R.drawable.science1, "Which chemical element is represented by the symbol \"Fe\"?", "Fluorine", "Phosphorus", "Iron", 3),
            QuizData(
                R.drawable.science1,
                "What is the name of the process by which plants convert sunlight into energy?",
                "Breathing",
                "Photosynthesis",
                "Fermentation",
                2
            ),
            QuizData(R.drawable.science1, "What gas is the main component of the Earth's atmosphere?", "Oxygen", "Carbon dioxide", "Nitrogen", 3),
            QuizData(R.drawable.science1, "What is the science of stars, planets and space called?", "Astronomy", "Physics", "Chemistry", 1),
            QuizData(R.drawable.science1, "Which organ is responsible for filtering blood in humans?", "Liver", "Heart", "Kidneys", 3),
            QuizData(
                R.drawable.science1,
                "What instrument is used to measure the strength of earthquakes?",
                "Thermometer",
                "Barometer",
                "Seismograph",
                3
            ),
            QuizData(R.drawable.science1, "What is the smallest unit of a chemical element called?", "Proton", "Atom", "Neutron", 2),
            QuizData(R.drawable.science1, "Which of these materials is not a good conductor of electricity?", "Copper", "Silver", "Rubber", 3)
        )
    }

    override fun getMathQuizzes(): List<QuizData> {
        return arrayListOf(
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1)
        )
    }

    override fun getAnimalQuizzes(): List<QuizData> {
        return arrayListOf(
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1)
        )
    }

    override fun getCapitalQuizzes(): List<QuizData> {
        return arrayListOf(
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1),
            QuizData(R.drawable.sport, "What is the most famous sport in the world?", "", "", "", 1)
        )
    }
}