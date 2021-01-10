using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using Matematik5_0.Data;
using Matematik5_0.Models.Forum;
using Matematik5_0.Models;

namespace Matematik5_0.Controllers
{
    public class CommentsController : Controller
    {
        private readonly Service _service;

        public CommentsController ( Service service )
        {
            _service = service;
        }

        //// GET: Comments
        //public async Task<IActionResult> Index()
        //{
        //    var applicationDbContext = _context.Comment.Include(c => c.Post);
        //    return View(await applicationDbContext.ToListAsync());
        //}

        // GET: Comments/Details/5
        //public async Task<IActionResult> Details(int? id)
        //{
        //    if (id == null)
        //    {
        //        return NotFound();
        //    }

        //    var comment = await _context.Comment
        //        .Include(c => c.Post)
        //        .FirstOrDefaultAsync(m => m.ID == id);
        //    if (comment == null)
        //    {
        //        return NotFound();
        //    }

        //    return View(comment);
        //}

        // GET: Comments/Create
        public IActionResult Create ( int id )
        {
            ViewBag.PostID = id;
            return View();
        }

        // POST: Comments/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create ( [Bind("Content,CreatedWhen,Author,UserID,PostID")] Comment comment )
        {
            if (ModelState.IsValid)
            {
                var id = comment.PostID;
                await _service.PostCommentAsync(comment);
                return RedirectToAction("Blog", "Posts", new { id });
            }
            return View(comment);
        }

        // GET: Comments/Edit/5
        //public async Task<IActionResult> Edit(int? id)
        //{
        //    if (id == null)
        //    {
        //        return NotFound();
        //    }

        //    var comment = await _context.Comment.FindAsync(id);
        //    if (comment == null)
        //    {
        //        return NotFound();
        //    }
        //    ViewData["PostID"] = new SelectList(_context.Set<Post>(), "ID", "ID", comment.PostID);
        //    return View(comment);
        //}

        // POST: Comments/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        //[HttpPost]
        //[ValidateAntiForgeryToken]
        //public async Task<IActionResult> Edit(int id, [Bind("ID,Content,CreatedWhen,Author,UserID,PostID")] Comment comment)
        //{
        //    if (id != comment.ID)
        //    {
        //        return NotFound();
        //    }

        //    if (ModelState.IsValid)
        //    {
        //        try
        //        {
        //            _context.Update(comment);
        //            await _context.SaveChangesAsync();
        //        }
        //        catch (DbUpdateConcurrencyException)
        //        {
        //            if (!CommentExists(comment.ID))
        //            {
        //                return NotFound();
        //            }
        //            else
        //            {
        //                throw;
        //            }
        //        }
        //        return RedirectToAction(nameof(Index));
        //    }
        //    ViewData["PostID"] = new SelectList(_context.Set<Post>(), "ID", "ID", comment.PostID);
        //    return View(comment);
        //}

        // GET: Comments/Delete/5
        //public async Task<IActionResult> Delete(int? id)
        //{
        //    if (id == null)
        //    {
        //        return NotFound();
        //    }

        //    var comment = await _context.Comment
        //        .Include(c => c.Post)
        //        .FirstOrDefaultAsync(m => m.ID == id);
        //    if (comment == null)
        //    {
        //        return NotFound();
        //    }

        //    return View(comment);
        //}

        // POST: Comments/Delete/5
        //[HttpPost, ActionName("Delete")]
        //[ValidateAntiForgeryToken]
        //public async Task<IActionResult> DeleteConfirmed(int id)
        //{
        //    var comment = await _context.Comment.FindAsync(id);
        //    _context.Comment.Remove(comment);
        //    await _context.SaveChangesAsync();
        //    return RedirectToAction(nameof(Index));
        //}

        //private bool CommentExists(int id)
        //{
        //    return _context.Comment.Any(e => e.ID == id);
        //}
    }
}
