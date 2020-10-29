from flask import Flask
from flask import request
from my_clustering import encode, clustering
# from urllib.parse import quote # url 한글 인코딩 Error 해결

app = Flask(__name__)

@app.route('/cluster')
def cluster():
    groupId = request.args.get('groupId', 'groupId')
    imagePath = request.args.get('imagePath', 'imagePath')
    print(groupId)
    print(imagePath)

    encode(groupId, [imagePath])
    result = clustering(groupId)
    print("결과!!!")
    print(result)

    return result


@app.route('/getInfo')
def getInfo():
    groupId = request.args.get('groupId', 'groupId')
    print(groupId)
    result = clustering(groupId)
    return result

if __name__ == '__main__':
    app.run()
