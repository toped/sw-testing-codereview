

import tkinter as tk
from TradeStation.ts_check_controller import *

text = ''



window = tk.Tk()
window.title('TradeStation ETB')

ticker_text = tk.StringVar()
response_text = tk.StringVar()

tk.Label(window, text='TradeStation ETB').grid(row=0, column=0, sticky='w')

ticker = tk.Entry(window, textvariable=ticker_text)


ticker.grid(row=0, column=1, sticky='w')
# tk.Button(window, text='Check', command=lambda: check_ticker(ticker_text.get())).grid(row=0, column=11, sticky='e')
check_button = tk.Button(window, text='Check')
check_button.grid(row=0, column=4, sticky='w')

response = tk.Label(window, textvariable=response_text)
response.grid(row=2, columnspan=50)

def button_clicked(ticker):
    global text
    text = check_ticker(ticker)
    response_text.set(text)

check_button.bind('<Button-1>', lambda event: button_clicked(ticker_text.get()))
ticker.bind('<KeyPress-Return>', lambda event: button_clicked(ticker_text.get()))



window.mainloop()


