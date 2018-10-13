import tornado.web
import json
import copy


class FinanceMovementHandler(tornado.web.RequestHandler):

    def initialize(self, *args, **kwargs):
        self.elasticsearch = kwargs['elasticsearch']

    def get(self, cpf):
        res = self.elasticsearch.get(index="users", doc_type='user', id=cpf)
        self.write({
            'movements': res['_source'].get('movements', [])
        })

    def post(self, cpf):
        new_finance_movement = json.loads(self.request.body)

        document_retrieved = self.elasticsearch.get(index="users", doc_type='user', id=cpf)

        new_document = self.push_finance_movement_to_document(document_retrieved['_source'], new_finance_movement)

        res = self.elasticsearch.index(index="users", doc_type='user', id=cpf, body=new_document)

        self.write({
            'result': res['result']
        })

    def push_finance_movement_to_document(self, document_retrieved, new_finance_movement):
        all_movements = document_retrieved.get('movements', [])

        new_movement_list = [new_finance_movement]
        new_movement_list.extend(all_movements)

        new_document = copy.deepcopy(document_retrieved)
        new_document['movements'] = new_movement_list

        if 'credit-card' == new_finance_movement['mode']:
            new_document['last_movement'] = new_finance_movement

        return new_document
