
from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, Field
from typing import Optional

#Create FastAPI application
app = FastAPI(
    title="Fertilizer Product Management API",
    description="API to manage fertilizer products",
    version="1.0.0"
)


# Create Fertilizer Model / Schema
class Fertilizer(BaseModel):
    product_name: str = Field(..., min_length=3)
    fertilizer_type: str
    category: str
    price: float = Field(..., gt=0)
    quantity: int = Field(..., ge=0)
    description: Optional[str] = None


# fertilizer data
fertilizers = {
    1: {
        "product_name": "Urea",
        "fertilizer_type": "Granular",
        "category": "Nitrogen",
        "price": 300.0,
        "quantity": 100,
        "description": "Nitrogen-based fertilizer"
    },
    2: {
        "product_name": "DAP",
        "fertilizer_type": "Granular",
        "category": "Phosphorus",
        "price": 1350.0,
        "quantity": 50,
        "description": "Diammonium phosphate fertilizer"
    },
    3: {
        "product_name": "NPK 19:19:19",
        "fertilizer_type": "Water Soluble",
        "category": "Compound",
        "price": 1200.0,
        "quantity": 75,
        "description": "Balanced NPK fertilizer"
    },
    4: {
        "product_name": "MOP",
        "fertilizer_type": "Granular",
        "category": "Potassium",
        "price": 900.0,
        "quantity": 60,
        "description": "Muriate of Potash fertilizer"
    }
}


# POST - Add a fertilizer
@app.post("/fertilizers")
def add_fertilizer(fertilizer: Fertilizer):

    product_id = max(fertilizers.keys(), default=0) + 1

    fertilizers[product_id] = fertilizer.model_dump()

    return {
        "message": "Fertilizer added successfully",
        "product_id": product_id,
        "product": fertilizers[product_id]
    }


#GET - Retrieve all fertilizers
@app.get("/fertilizers")
def get_fertilizers(
    category: Optional[str] = None,
    limit: int = 10
):

    result = fertilizers

    if category:
        result = {
            product_id: product
            for product_id, product in fertilizers.items()
            if product["category"].lower() == category.lower()
        }

    return dict(list(result.items())[:limit])


# GET - Retrieve fertilizer using ID
@app.get("/fertilizers/{product_id}")
def get_fertilizer(product_id: int):

    if product_id not in fertilizers:
        raise HTTPException(
            status_code=404,
            detail="Fertilizer product not found"
        )

    return fertilizers[product_id]


# PUT - Update fertilizer
@app.put("/fertilizers/{product_id}")
def update_fertilizer(
    product_id: int,
    fertilizer: Fertilizer
):

    if product_id not in fertilizers:
        raise HTTPException(
            status_code=404,
            detail="Fertilizer product not found"
        )

    fertilizers[product_id] = fertilizer.model_dump()

    return {
        "message": "Fertilizer updated successfully",
        "product": fertilizers[product_id]
    }


#DELETE - Delete fertilizer
@app.delete("/fertilizers/{product_id}")
def delete_fertilizer(product_id: int):

    if product_id not in fertilizers:
        raise HTTPException(
            status_code=404,
            detail="Fertilizer product not found"
        )

    deleted_product = fertilizers.pop(product_id)

    return {
        "message": "Fertilizer deleted successfully",
        "product": deleted_product
    }

