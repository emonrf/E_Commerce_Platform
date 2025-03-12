import React from 'react';

function ProductList({ products, onAddToCart }) {
  return (
    <div className="row">
      {products.map((product) => (
        <div key={product.id} className="col-lg-4 col-md-6 mb-4">
          <div className="card h-100">
            {product.imageUrl && (
              <img 
                src={product.imageUrl} 
                className="card-img-top" 
                alt={product.name}
                style={{ height: '200px', objectFit: 'cover' }}
              />
            )}
            <div className="card-body">
              <h5 className="card-title">{product.name}</h5>
              <p className="card-text">${product.price.toFixed(2)}</p>
              <p className="card-text"><small className="text-muted">{product.category.name}</small></p>
              <button 
                className="btn btn-sm btn-primary"
                onClick={() => onAddToCart(product)}
              >
                Add to Cart
              </button>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}

export default ProductList;