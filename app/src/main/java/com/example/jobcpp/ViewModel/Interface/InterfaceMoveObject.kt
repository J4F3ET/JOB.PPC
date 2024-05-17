package ud.ppc.ViewModel.Interface

import ud.ppc.Model.Interface.InterfaceBoard
import ud.ppc.Model.Interface.InterfaceBox
import ud.ppc.Utils.MovementDirection

interface InterfaceMoveObject {
    fun validateMove(box:InterfaceBox,board: InterfaceBoard<InterfaceBox>):Boolean
    fun executeMovement(
        direction:MovementDirection,box: InterfaceBox,board: InterfaceBoard<InterfaceBox>
    ):InterfaceBoard<InterfaceBox>
}