

from PyQt5.QtWidgets import QApplication, QWidget, QPushButton

class Example(QWidget):

    def __init__(self):
        QWidget.__init__(self)
        self.button = QPushButton('Ticker', self)
        self.init_ui()

    def init_ui(self):
        # self.setGeometry(500, 600)
        self.setWindowTitle('Stock Info')
        # self.setWindowIcon(QIcon('web.png'))

        self.button.move(20, 20)

        self.show()

