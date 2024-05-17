package ud.ppc.Model.DTO

import ud.ppc.Model.Interface.InterfaceBoard
import ud.ppc.Model.Interface.InterfaceBox

data class GameState(
    val board:InterfaceBoard<InterfaceBox>,
    var score: Int,
    var best: Int
)
