import tornado.web


class LastPurchaseHandler(tornado.web.RequestHandler):

    def initialize(self, *args, **kwargs):
        self.elasticsearch = kwargs['elasticsearch']

    def get(self, cpf):
        res = self.elasticsearch.get(index="users", doc_type='user', id=cpf)
        self.write(res['_source'].get('last_movement', {}))
