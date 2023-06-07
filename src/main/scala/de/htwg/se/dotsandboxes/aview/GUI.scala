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
    val panalSize = new Dimension(850, 750)
    val colorBackground = Color(245, 245, 245)
    val colorFont = Color(60, 60, 60)
    val colorStats = Color(220, 220, 220)

    val dot = ImageIcon(ImageIO.read(File("src/resources/0_Dot.png")))
    val logo = ImageIO.read(File("src/resources/0_Logo.png"))
    val menu = ImageIcon(ImageIO.read(File("src/resources/0_Menu.png")))
    val takenBar = ImageIcon(ImageIO.read(File("src/resources/1_BarTaken.png")))
    val untakenBar = ImageIcon(ImageIO.read(File("src/resources/1_BarUntaken.png")))
    val takenCol = ImageIcon(ImageIO.read(File("src/resources/1_ColTaken.png")))
    val untakenCol = ImageIcon(ImageIO.read(File("src/resources/1_ColUntaken.png")))
    val white = ImageIcon(ImageIO.read(File("src/resources/2_1TakenEmpty.png")))
    val takenBlue = ImageIcon(ImageIO.read(File("src/resources/2_TakenBlue.png")))
    val takenRed = ImageIcon(ImageIO.read(File("src/resources/2_TakenRed.png")))
    val takenGreen = ImageIcon(ImageIO.read(File("src/resources/2_TakenGreen.png")))
    val takenYellow = ImageIcon(ImageIO.read(File("src/resources/2_TakenYellow.png")))
    val playerBlue = ImageIcon(ImageIO.read(File("src/resources/3_PlayerBlue.png")))
    val playerRed = ImageIcon(ImageIO.read(File("src/resources/3_PlayerRed.png")))
    val playerGreen = ImageIcon(ImageIO.read(File("src/resources/3_PlayerGreen.png")))
    val playerYellow = ImageIcon(ImageIO.read(File("src/resources/3_PlayerYellow.png")))

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
    menuBar.background = colorBackground
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
        preferredSize = panalSize
        resizable = false
        background = colorBackground
        val player = playerCondition
        val stats = playerStats
        add(player, BorderPanel.Position.North)
        add(CellPanel(fieldSize._1, fieldSize._2), BorderPanel.Position.Center)
        add(stats, BorderPanel.Position.South)}

    def playerTurn = new FlowPanel {
        background = colorBackground
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
        label.foreground = colorFont
        label.font = Font("Comic Sans MS", 0, 35)
        contents += label
    }

    def playerStats = new GridBagPanel {
        val color = colorStats
        val score = TextArea(controller.stats.replace("\n", "   |   "))
        val con = new Constraints
        background = color
        score.background = color
        score.font = Font("Comic Sans MS", 0, 17)
        score.foreground = colorFont
        score.editable = false
        con.anchor = Anchor.Center
        layout(score) = con}

    def playerResult = new FlowPanel {
        background = colorBackground
        override def paintComponent(g: Graphics2D): Unit = {
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
            super.paintComponent(g)}

        val fontType = Font("Comic Sans MS", 0, 35)
        if controller.winner == "It's a draw!" then
            contents += new Label {
                val label = Label(controller.winner)
                label.font = fontType
                label.foreground = colorFont
                label.border = LineBorder(colorBackground, 10)
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
            label.foreground = colorFont
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
