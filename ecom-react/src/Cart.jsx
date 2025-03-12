import React from 'react';

function Cart({ isOpen, onClose, items, onUpdateQuantity, onRemove, onClear, total }) {
  if (!isOpen) return null;

  return (
    <div className="position-fixed top-0 end-0 h-100 bg-light p-4 shadow" style={{ 
      width: '350px', 
      zIndex: 1050,
      transform: isOpen ? 'translateX(0)' : 'translateX(100%)',
      transition: 'transform 0.3s ease-in-out',
      overflowY: 'auto'
    }}>
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h3 className="m-0">Shopping Cart</h3>
        <button className="btn-close" onClick={onClose}></button>
      </div>
      
      {items.length === 0 ? (
        <p>Your cart is empty</p>
      ) : (
        <>
          <div className="cart-items mb-4">
            {items.map(item => (
              <div key={item.id} className="card mb-3">
                <div className="card-body">
                  <h5 className="card-title">{item.name}</h5>
                  <p className="card-text">${item.price.toFixed(2)}</p>
                  <div className="d-flex justify-content-between align-items-center">
                    <div className="input-group" style={{ width: '120px' }}>
                      <button 
                        className="btn btn-outline-secondary" 
                        type="button"
                        onClick={() => onUpdateQuantity(item.id, item.quantity - 1)}
                      >
                        -
                      </button>
                      <input 
                        type="text" 
                        className="form-control text-center" 
                        value={item.quantity}
                        readOnly
                      />
                      <button 
                        className="btn btn-outline-secondary" 
                        type="button"
                        onClick={() => onUpdateQuantity(item.id, item.quantity + 1)}
                      >
                        +
                      </button>
                    </div>
                    <button 
                      className="btn btn-sm btn-danger"
                      onClick={() => onRemove(item.id)}
                    >
                      <i className="bi bi-trash"></i>
                    </button>
                  </div>
                  <div className="mt-2 text-end">
                    <strong>Subtotal: ${(item.price * item.quantity).toFixed(2)}</strong>
                  </div>
                </div>
              </div>
            ))}
          </div>
          
          <div className="card mb-3">
            <div className="card-body">
              <h5 className="card-title">Order Summary</h5>
              <div className="d-flex justify-content-between mb-2">
                <span>Subtotal:</span>
                <span>${total.toFixed(2)}</span>
              </div>
              <div className="d-flex justify-content-between mb-2">
                <span>Shipping:</span>
                <span>Free</span>
              </div>
              <div className="d-flex justify-content-between fw-bold">
                <span>Total:</span>
                <span>${total.toFixed(2)}</span>
              </div>
            </div>
          </div>
          
          <div className="d-grid gap-2">
            <button className="btn btn-primary">Checkout</button>
            <button className="btn btn-outline-secondary" onClick={onClear}>Clear Cart</button>
          </div>
        </>
      )}
    </div>
  );
}

export default Cart;