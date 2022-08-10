# Paynow-backend

<!-- Send Money Api call -->

METHOD: POST, 
URL: localhost:8080/sendMoney

REQUEST: {
    "senderDetails": {
        "id": "69652133523248", 
        "name": "HDFC BANK -(CHENNAI)", 
        "balance": 1000,
        "od": "yes"
    }, 
    "receiverDetails": {
        "bic": "ANDBINBBXXX", 
        "bank": "HDFC BANK"
    }, 
    "moneyToSend": 1000,
    "transactionType": "bank"
}

RESPONSE: {
    "balanceRemaining": 0,
    "code": "200",
    "status": "success"
}

<!-- Search banks -->

METHOD: POST, 
URL: localhost:8080/searchBank

REQUEST: {
    "details": "INDIAN"
}

RESPONSE: 
    {
        "bank": "INDIAN BANK",
        "code": "",
        "bic": "IDIBINBBXXX"
    },
    {
        "bank": "INDIAN OVERSEAS BANK",
        "code": "",
        "bic": "IOBAINBBXXX"
    },
    {
        "bank": "SOUTH INDIAN BANK LTD., THE",
        "code": "",
        "bic": "SOININ55179"
    }
]

METHOD: GET
URL: localhost:8080/findById?id=69652133523248

RESPONSE: 
{
    "isFound": {
        "id": "69652133523248",
        "name": "HDFC BANK -(CHENNAI)",
        "balance": 0,
        "type": "bank transfer",
        "od": "yes"
    },
    "status": 200
}

or

{
    "isFound": {
        "id": null,
        "name": "HDFC BANK -(CHENNAI)",
        "balance": 0,
        "type": "bank transfer",
        "od": "yes"
    },
    "status": 200
}

METHOD: POST

REQUEST: 
 {
        "amount": 25000,
        "beneficiary_ACNUM": 98765432,
        "utr_NUMBER": 12334457,
        "status": false, 
        "sender_ACNUM": 132456,
        "dot": "2017-06-18"
    }
    
RESPONSE: 
'ADDED transaction successfully"
