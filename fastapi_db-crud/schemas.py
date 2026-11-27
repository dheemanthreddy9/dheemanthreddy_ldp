
from pydantic import BaseModel, ConfigDict


# Schema for creating a fertilizer
class FertilizerCreate(BaseModel):
    name: str
    brand: str
    fertilizer_type: str
    price: float


# Schema for returning fertilizer details
class FertilizerResponse(BaseModel):
    id: int
    name: str
    brand: str
    fertilizer_type: str
    price: float

    model_config = ConfigDict(from_attributes=True)
