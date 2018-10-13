# from handlers.histograma_handler import HistogramaHandler
# from handlers.resultado_handler import ResultadoHandler
# from handlers.votacao_handler import VotacaoHandler
from handlers.status_handler import StatusHandler
from handlers.cpf_handler import CpfHandler
from handlers.finance_movement_handler import FinanceMovementHandler
import tornado.web


def create_web_app(elasticsearch):
    return tornado.web.Application([
        (r"/status", StatusHandler, {'foo': 'bah'}),

        # movimentacao financeira
        # dados da ultima compra

        (r"/cpf/(\d{11})/?", CpfHandler, {'elasticsearch': elasticsearch}),
        (r"/finance-movement/(\d{11})/?", FinanceMovementHandler, {'elasticsearch': elasticsearch}),

    ])
