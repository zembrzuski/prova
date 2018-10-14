import tornado.web
import json


class CpfHandler(tornado.web.RequestHandler):

    def initialize(self, *args, **kwargs):
        self.elasticsearch = kwargs['elasticsearch']

    def get(self, cpf):
        res = self.elasticsearch.get(index="users", doc_type='user', id=cpf)
        self.write(res['_source'])

    def post(self, cpf):
        body_json = json.loads(self.request.body)
        body_json['cpf'] = cpf

        res = self.elasticsearch.index(index="users", doc_type='user', id=cpf, body=body_json)

        self.write({
            'result': res['result']
        })
