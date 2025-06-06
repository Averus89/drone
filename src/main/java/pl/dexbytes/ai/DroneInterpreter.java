package pl.dexbytes.ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import pl.dexbytes.model.AiInstructionsResponse;

@RegisterAiService
public interface DroneInterpreter {

    @SystemMessage("""
            Jesteś pomocnym asystentem, który na podstawie otrzymanych instrukcji jest w stanie odpowiadać na jakim terenie wyląduje dron.
            Dron zawsze zaczyna w pozycji [0][0] "Punkt startowy". Jeśli nie dostaniesz instrukcji - zwracaj zawsze punkt [0][0].
            W lewo znaczy [x][y-1]
            W prawo znaczy [x][y+1]
            W dół znaczy [x+1][y]
            W górę znaczy [x-1][y]
            
            Teren reprezentuje tablica 2 wymiarowa [0..3][0..3]
            <table>
            [
              ["Punkt startowy", "trawa", "jedno drzewo", "budynek"],
              ["trawa", "młyn", "trawa", "trawa"],
              ["trawa", "trawa", "skały", "dwa drzewa"],
              ["góry", "góry", "samochód", "jaskinia"]
            ]
            </table>
            
            Odpowiadasz za pomocą poniższego formatu
            <format>
            {
              "_thinking": opisz swój proces myślowy,
              "description": opis z mapy
            }
            </format>
            
            Przykładowe odpowiedzi
            <example>
            USER: Najpierw poleciałem w prawo, następnie na sam dół
            AI: {
              "_thinking": "Operator chciał polecieć jedno pole w prawo, a następnie na sam dół",
              "description": "góry"
            }
            
            USER: Prawo, dół, lewo, dół, dół
            AI: {
              "_thinking": "Myślę, że użytkownik chciał polecieć [0][1] -> [1][1] -> [1][0] -> [2][0] -> [3][0]",
              "description": "góry"
            }
            
            USER: do końca w prawo, a następnie na sam dół, potem w lewo
            AI: {
              "_thinking": "Najpierw lecimy do [0][3], następnie [3][3], potem [3][2]",
              "description": "samochód"
            }
            
            USER:""
            AI: {
              "_thinking": "Najpierw lecimy do [0][3], następnie [3][3], potem [3][2]",
              "description": "Punkt startowy"
            }
            
            USER:"do góry"
            AI: {
              "_thinking": "Nie możemy latać na ujemne pozycje[-1][0]",
              "description": "Punkt startowy"
            }
            
            USER:"całkiem do góry i w lewo"
            AI: {
              "_thinking": "Nie możemy latać na ujemne pozycje [-1][-1]",
              "description": "Punkt startowy"
            }
            </example>
            """)
    AiInstructionsResponse interpret(@UserMessage String instruction);
}
