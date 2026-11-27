
from fastapi import FastAPI, Depends, HTTPException, status
from sqlalchemy.orm import Session

import models
import schemas
from database import engine, get_db


# Create FastAPI application
app = FastAPI(title="Fertilizer Management API")


# Create database tables
models.Base.metadata.create_all(bind=engine)


# CREATE - Insert a fertilizer
@app.post(
    "/fertilizers",
    response_model=schemas.FertilizerResponse,
    status_code=status.HTTP_201_CREATED
)
def create_fertilizer(
    fertilizer: schemas.FertilizerCreate,
    db: Session = Depends(get_db)
):
    new_fertilizer = models.Fertilizer(
        name=fertilizer.name,
        brand=fertilizer.brand,
        fertilizer_type=fertilizer.fertilizer_type,
        price=fertilizer.price
    )

    db.add(new_fertilizer)
    db.commit()
    db.refresh(new_fertilizer)

    return new_fertilizer


# READ - Retrieve all fertilizers
@app.get(
    "/fertilizers",
    response_model=list[schemas.FertilizerResponse]
)
def get_all_fertilizers(db: Session = Depends(get_db)):
    fertilizers = db.query(models.Fertilizer).all()

    return fertilizers


# READ - Retrieve a fertilizer by ID
@app.get(
    "/fertilizers/{fertilizer_id}",
    response_model=schemas.FertilizerResponse
)
def get_fertilizer(
    fertilizer_id: int,
    db: Session = Depends(get_db)
):
    fertilizer = db.query(models.Fertilizer).filter(
        models.Fertilizer.id == fertilizer_id
    ).first()

    if fertilizer is None:
        raise HTTPException(
            status_code=404,
            detail="Fertilizer not found"
        )

    return fertilizer


# UPDATE - Update a fertilizer
@app.put(
    "/fertilizers/{fertilizer_id}",
    response_model=schemas.FertilizerResponse
)
def update_fertilizer(
    fertilizer_id: int,
    fertilizer: schemas.FertilizerCreate,
    db: Session = Depends(get_db)
):
    db_fertilizer = db.query(models.Fertilizer).filter(
        models.Fertilizer.id == fertilizer_id
    ).first()

    if db_fertilizer is None:
        raise HTTPException(
            status_code=404,
            detail="Fertilizer not found"
        )

    db_fertilizer.name = fertilizer.name
    db_fertilizer.brand = fertilizer.brand
    db_fertilizer.fertilizer_type = fertilizer.fertilizer_type
    db_fertilizer.price = fertilizer.price

    db.commit()
    db.refresh(db_fertilizer)

    return db_fertilizer


# DELETE - Delete a fertilizer
@app.delete("/fertilizers/{fertilizer_id}")
def delete_fertilizer(
    fertilizer_id: int,
    db: Session = Depends(get_db)
):
    fertilizer = db.query(models.Fertilizer).filter(
        models.Fertilizer.id == fertilizer_id
    ).first()

    if fertilizer is None:
        raise HTTPException(
            status_code=404,
            detail="Fertilizer not found"
        )

    db.delete(fertilizer)
    db.commit()

    return {"message": "Fertilizer deleted successfully"}
