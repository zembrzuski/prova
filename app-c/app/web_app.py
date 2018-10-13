# from handlers.histograma_handler import HistogramaHandler
# from handlers.resultado_handler import ResultadoHandler
# from handlers.votacao_handler import VotacaoHandler
from handlers.status_handler import StatusHandler

import tornado.web


def create_web_app():
    return tornado.web.Application([
        (r"/status", StatusHandler, {'foo': 'bah'}),

        # ultima consulta
        # movimentacao financeira
        # dados da ultima compra

        # post globalzao

        # (r"/backend/resultado", ResultadoHandler, {'redis': redis_client}),
        # (r"/backend/histograma", HistogramaHandler, {'redis': redis_client}),
    ])
