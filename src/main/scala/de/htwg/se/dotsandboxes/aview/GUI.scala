package de.htwg.se.dotsandboxes
package aview

import controller.Controller
import model.Move
import util.{Observer, Event}

import scala.swing.*
import scala.swing.event.*
import scala.swing.GridBagPanel.Anchor
import java.io.File
import java.awt.Color
import java.awt.Font
import java.awt.RenderingHints
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.UIManager
import javax.swing.border.LineBorder

class GUI(controller: Controller) extends Frame with Observer:
    controller.add(this)

    val fieldSize: (Int, Int) = (controller.colSize(1, 0), controller.rowSize(2))
    val gridSize: (Int, Int) = ((fieldSize._1 + fieldSize._1 + 1), (fieldSize._2 + fieldSize._2 + 1))

    val dot = ImageIcon(ImageIO.read(File("src/resources/0_Dot.jpg")))
    val logo = ImageIO.read(File("src/resources/0_Logo.png"))
    val menu = ImageIcon(ImageIO.read(File("src/resources/0_Menu.png")))
    val takenBar = ImageIcon(ImageIO.read(File("src/resources/1_BarTaken.png")))
    val untakenBar = ImageIcon(ImageIO.read(File("src/resources/1_BarUntaken.png")))
    val takenCol = ImageIcon(ImageIO.read(File("src/resources/1_ColTaken.png")))
    val untakenCol = ImageIcon(ImageIO.read(File("src/resources/1_ColUntaken.png")))
    val white = ImageIcon(ImageIO.read(File("src/resources/2_White.png")))
    val takenBlue = ImageIcon(ImageIO.read(File("src/resources/2_BlueTaken.png")))
    val takenRed = ImageIcon(ImageIO.read(File("src/resources/2_RedTaken.png")))
    val takenGreen = ImageIcon(ImageIO.read(File("src/resources/2_GreenTaken.png")))
    val takenYellow = ImageIcon(ImageIO.read(File("src/resources/2_YellowTaken.png")))
    val playerBlue = ImageIcon(ImageIO.read(File("src/resources/3_PlayerBlue.jpg")))
    val playerRed = ImageIcon(ImageIO.read(File("src/resources/3_PlayerRed.jpg")))
    val playerGreen = ImageIcon(ImageIO.read(File("src/resources/3_PlayerGreen.jpg")))
    val playerYellow = ImageIcon(ImageIO.read(File("src/resources/3_PlayerYellow.jpg")))
    
    title = "Dots And Boxes"
    iconImage = logo
    menuBar = new MenuBar {
        contents += new Menu("") {
            icon = menu
            borderPainted = false
            contents += MenuItem(Action("Exit") { controller.abort })
            contents += MenuItem(Action("Undo") { controller.publish(controller.undo) })
            contents += MenuItem(Action("Redo") { controller.publish(controller.redo) })
        }
    }
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)
    menuBar.border = Swing.EmptyBorder(0, 1, 0, 0)
    menuBar.background = Color.WHITE
    update(Event.Move)
    centerOnScreen
    pack
    open()

    override def update(event: Event): Unit = event match
        case Event.Abort => sys.exit
        case Event.End   => contents = revise(playerResult); repaint
        case Event.Move  => contents = revise(playerTurn); repaint

    override def closeOperation = controller.abort

    def revise(playerCondition: FlowPanel) = new BorderPanel {
        val dim = new Dimension(850, 750)
        minimumSize = dim
        maximumSize = dim
        preferredSize = dim
        resizable = false
        background = Color.WHITE
        val player = playerCondition
        val stats = playerStats
        add(player, BorderPanel.Position.North)
        add(CellPanel(fieldSize._1, fieldSize._2), BorderPanel.Position.Center)
        add(stats, BorderPanel.Position.South)}

    def playerTurn = new FlowPanel {
        background = Color.WHITE
        contents += new Label {
            override def paintComponent(g: Graphics2D): Unit = {
                g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
                super.paintComponent(g)}

            icon = controller.currentPlayer.toString match
                case "Blue" => playerBlue
                case "Red"  => playerRed
                case "Green" => playerGreen
                case "Yellow"  => playerYellow
        }
        val label = Label(s" Turn [points: ${controller.currentPoints}]")
        label.font = Font("Comic Sans MS", 0, 35)
        contents += label
    }

    def playerStats = new GridBagPanel {
        val color = Color(220, 220, 220)
        val score = TextArea(controller.stats.replace("\n", "   |   "))
        val con = new Constraints
        background = color
        score.background = color
        score.font = Font("Comic Sans MS", 0, 17)
        score.editable = false
        con.anchor = Anchor.Center
        layout(score) = con}

    def playerResult = new FlowPanel {
        background = Color.WHITE
        override def paintComponent(g: Graphics2D): Unit = {
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
            super.paintComponent(g)}

        val fontType = Font("Comic Sans MS", 0, 35)
        if controller.winner == "It's a draw!" then
            contents += new Label {
                val label = Label(controller.winner)
                label.font = fontType
                label.border = LineBorder(Color.WHITE, 10)
                contents += label}
        else 
            contents += new Label {
                icon = controller.winner.substring(7) match
                    case "Blue wins!" => playerBlue
                    case "Red wins!" => playerRed
                    case "Green wins!" => playerGreen
                    case "Yellow wins!" => playerYellow}
            val label = Label(" wins!")
            label.font = fontType
            contents += label}


    class CellPanel(x: Int, y: Int) extends GridPanel(gridSize._2, gridSize._1):
        opaque = false
        fieldBuilder

        private def fieldBuilder =
            for (i <- 0 until y)
                for (j <- 0 until x) bar(i, j)
                contents += dotImg
                for (j <- 0 until x + 1) cell(i, j)
            for (j <- 0 until x) bar(y, j)
            contents += dotImg

        private def bar(x: Int, y: Int) =
            contents += dotImg
            contents += CellButton(1, x, y, controller.get(1, x, y).toString.toBoolean)

        private def cell(x: Int, y: Int) =
            contents += CellButton(2, x, y, controller.get(2, x, y).toString.toBoolean)
            if(y != this.x) contents += new Label {
                icon = controller.get(0, x, y).toString match
                    case "-" => white
                    case "B" => takenBlue
                    case "R" => takenRed
                    case "G" => takenGreen
                    case "Y" => takenYellow}

        private def dotImg = new Label {
            override def paintComponent(g: Graphics2D): Unit = {
                g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
                super.paintComponent(g)} 
            icon = dot}


    class CellButton(vec: Int, x: Int, y: Int, status: Boolean) extends Button:
        borderPainted = false
        focusPainted = false
        opaque = false

        icon = vec match
            case 1 => status match
                case true => takenBar
                case false => white
            case 2 => status match
                case true => takenCol
                case false => white

        listenTo(mouse.moves, mouse.clicks)
        reactions += {
            case MouseClicked(src, pt, mod, clicks, props) =>
                controller.publish(controller.put, Move(vec, x, y, true))

            case MouseEntered(source) => vec match
                case 1 => status match
                    case true =>
                    case false => icon = untakenBar
                case 2 => status match
                    case true =>
                    case false => icon = untakenCol

            case MouseExited(source) => status match
                case true =>
                case false => icon = white}
