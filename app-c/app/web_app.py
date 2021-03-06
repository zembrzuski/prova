from handlers.status_handler import StatusHandler
from handlers.cpf_handler import CpfHandler
from handlers.finance_movement_handler import FinanceMovementHandler
from handlers.last_purchase import LastPurchaseHandler
import tornado.web


def create_web_app(elasticsearch):
    return tornado.web.Application([
        (r"/status", StatusHandler, {'foo': 'bah'}),
        (r"/cpf/(\d{11})/?", CpfHandler, {'elasticsearch': elasticsearch}),
        (r"/finance-movement/(\d{11})/?", FinanceMovementHandler, {'elasticsearch': elasticsearch}),
        (r"/last-purchase/(\d{11})/?", LastPurchaseHandler, {'elasticsearch': elasticsearch}),
    ])
